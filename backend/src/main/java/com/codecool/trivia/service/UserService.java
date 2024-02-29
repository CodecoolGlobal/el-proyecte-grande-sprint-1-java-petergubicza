package com.codecool.trivia.service;

import com.codecool.trivia.dto.frontend_request.UserStatDTO;
import com.codecool.trivia.dto.payload.JwtResponse;
import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.enums.RoleName;
import com.codecool.trivia.dto.frontend_request.PointRequestDTO;
import com.codecool.trivia.dto.frontend_request.UserRequestDTO;
import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import com.codecool.trivia.security.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final ConsoleLogger logger = new ConsoleLogger();
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @Transactional
    public boolean createUser(UserRequestDTO userRequest) {
        try {
            String hashedPassword = encoder.encode(userRequest.password());

            TriviaUser newUser = new TriviaUser(
                    userRequest.name(),
                    hashedPassword
            );

            newUser.addRole(new Role(RoleName.ROLE_USER));

            userRepository.save(newUser);
            return true;

        } catch (Exception e) {
            logger.logError(e.toString());
            return false;
        }
    }

    public boolean addPointsToUser(PointRequestDTO pointRequest) {
        try {
            TriviaUser triviaUser = userRepository.findTriviaUserByName(pointRequest.name()).get();

            int userPoints = triviaUser.getPoints();
            int updatedPoints = userPoints + pointRequest.extraPoints();

            triviaUser.setPoints(updatedPoints);
            userRepository.save(triviaUser);
            return true;

        } catch (UsernameNotFoundException e) {
            logger.logError(e.toString());
            return false;
        }
    }

    @Transactional
    public void addRoleFor(TriviaUser triviaUser, Role role) {
        try {
            TriviaUser triviaUser1 = userRepository.findTriviaUserByName(triviaUser.getName()).get();
            Set<Role> oldRoles = triviaUser1.getRoles();

            Set<Role> copiedRoles = new HashSet<>(oldRoles);
            copiedRoles.add(role);

            userRepository.save(triviaUser1);
        } catch (UsernameNotFoundException e) {
            logger.logError(e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<?> login(UserRequestDTO userRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.name(), userRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        User userDetails = (User) authentication.getPrincipal();

        List<String> roles = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), roles));
    }

    public ResponseEntity<UserStatDTO> getUserStats(String authorization) {
        String jwt = authorization.substring(7);
        String userName = jwtUtils.getUserNameFromJwtToken(jwt);
        UserStatDTO userStatDTO = null;
        try {
            TriviaUser triviaUser = userRepository.findTriviaUserByName(userName).get();
            userStatDTO =
                    new UserStatDTO(
                            triviaUser.getName(),
                            triviaUser.getPoints(),
                            null);
        } catch (Exception e) {
            logger.logError("User not found" + e.getMessage());
        }
        return ResponseEntity.ok(userStatDTO);
    }
}
