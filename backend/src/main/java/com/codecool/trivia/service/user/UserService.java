package com.codecool.trivia.service.user;

import com.codecool.trivia.dto.frontend_request.user.*;
import com.codecool.trivia.dto.payload.JwtResponse;
import com.codecool.trivia.model.entity.Difficulty;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.enums.RoleName;
import com.codecool.trivia.dto.frontend_request.leaderboard.PointRequestDTO;
import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.DifficultyRepository;
import com.codecool.trivia.repository.QuestionRepository;
import com.codecool.trivia.repository.UserRepository;
import com.codecool.trivia.security.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.*;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final DifficultyRepository difficultyRepository;
  private final QuestionRepository questionRepository;
  private static final ConsoleLogger logger = new ConsoleLogger();
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final PasswordEncoder encoder;
  private final static Map<String, Integer> REWARD_TABLE = new HashMap<>() {{
    put("easy", 1);
    put("medium", 3);
    put("hard", 5);
  }};

  @Autowired
  public UserService(UserRepository userRepository, DifficultyRepository difficultyRepository, QuestionRepository questionRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.difficultyRepository = difficultyRepository;
    this.questionRepository = questionRepository;
    this.authenticationManager = authenticationManager;
    this.jwtUtils = jwtUtils;
    this.encoder = encoder;
  }

  @Transactional
  public ResponseEntity<?> createUser(UserNamePasswordDTO userRequest) {
    try {
      String hashedPassword = encoder.encode(userRequest.password());

      TriviaUser newUser = new TriviaUser(
              userRequest.name(),
              hashedPassword
      );

      newUser.addRole(new Role(RoleName.ROLE_USER));

      userRepository.save(newUser);
      return ResponseEntity.ok(new UserNameDTO(newUser.getName()));

    } catch (Exception e) {
      logger.logError("Couldn't create user: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't create user");
    }
  }

  public ResponseEntity<?> addPointsToUser(PointRequestDTO pointRequest) {
    try {
      TriviaUser triviaUser = userRepository.findTriviaUserByName(pointRequest.name()).get();
      int userPoints = triviaUser.getPoints();

      Question question = questionRepository.findById(pointRequest.questionId()).get();
      Difficulty difficulty = difficultyRepository.findDifficultyByQuestions(question);
      int extraPoints = REWARD_TABLE.get(difficulty.getName());

      int updatedPoints = userPoints + extraPoints;

      triviaUser.setPoints(updatedPoints);
      userRepository.save(triviaUser);

      return ResponseEntity.ok("Points added to user!");

    } catch (UsernameNotFoundException e) {
      logger.logError("Couldn't add points to user: " + e.getMessage());
      return ResponseEntity.badRequest().body("Couldn't add points to user!");
    }
  }

  @Transactional
  public ResponseEntity<?> addRoleFor(NewRoleDTO newRoleForUser) {
    try {
      TriviaUser triviaUser = userRepository.findTriviaUserByName(newRoleForUser.name()).get();
      Set<Role> oldRoles = triviaUser.getRoles();

      Set<Role> copiedRoles = new HashSet<>(oldRoles);
      copiedRoles.add(new Role(RoleName.valueOf(newRoleForUser.newRole())));
      triviaUser.setRoles(copiedRoles);

      userRepository.save(triviaUser);
      return ResponseEntity.ok(new UserRolesDTO(triviaUser.getName(), triviaUser.getRoles()));

    } catch (UsernameNotFoundException e) {
      logger.logError(e.getMessage());
      return ResponseEntity.badRequest().body("Couldn't add role for user");
    }
  }

  @Transactional
  public ResponseEntity<?> login(UserNamePasswordDTO userRequest) {

    try {
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

    } catch (Exception e) {
      logger.logError("Couldn't log user in: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't log user in");
    }
  }

  public ResponseEntity<?> getUserStats(String authorization) {

    try {
      String jwt = authorization.substring(7);
      String userName = jwtUtils.getUserNameFromJwtToken(jwt);

      TriviaUser triviaUser = userRepository.findTriviaUserByName(userName).get();

      UserStatDTO userStatDTO = new UserStatDTO(
              triviaUser.getName(),
              triviaUser.getPoints(),
              null);

      return ResponseEntity.ok(userStatDTO);

    } catch (UsernameNotFoundException e) {
      logger.logError("User not found" + e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't find user");
    }
  }
}
