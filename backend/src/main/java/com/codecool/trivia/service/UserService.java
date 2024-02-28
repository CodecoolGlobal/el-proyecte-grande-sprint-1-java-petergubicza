package com.codecool.trivia.service;

import com.codecool.trivia.dto.payload.JwtResponse;
import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.enums.RoleName;
import com.codecool.trivia.model.request_schema.PointRequest;
import com.codecool.trivia.model.request_schema.UserRequest;
import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import com.codecool.trivia.security.jwt.JwtUtils;
import jakarta.transaction.Transactional;
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
  public boolean createUser(UserRequest userRequest) {
    try {
      String hashedPassword = encoder.encode(userRequest.password());

      TriviaUser newUser = new TriviaUser(
              userRequest.name(),
              hashedPassword
      );

      newUser.addRole(new Role(RoleName.ROLE_GUEST));

      userRepository.save(newUser);
      return true;

    } catch (Exception e) {
      logger.logError(e.toString());
      return false;
    }
  }

  public boolean addPointsToUser(PointRequest pointRequest) {
    try {
      TriviaUser triviaUser = userRepository.findTriviaUserByName(pointRequest.name()).get();

      double userPoints = triviaUser.getPoints();
      double updatedPoints = userPoints + pointRequest.extraPoints();

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
  public ResponseEntity<?> login(UserRequest userRequest) {
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
}
