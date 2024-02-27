package com.codecool.trivia.service;

import com.codecool.trivia.model.entity.Role;
import com.codecool.trivia.model.request_schema.PointRequest;
import com.codecool.trivia.model.request_schema.UserRequest;
import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final ConsoleLogger logger;

  public UserService(UserRepository userRepository, ConsoleLogger logger) {
    this.userRepository = userRepository;
    this.logger = logger;
  }

  public boolean createUser(UserRequest userRequest) {
    try {
      TriviaUser newUser = new TriviaUser(
              userRequest.name(),
              userRequest.password()
      );
      userRepository.save(newUser);
      return true;

    } catch (Exception e) {
      logger.logError(e.toString());
      return false;
    }
  }

  public boolean addPointsToUser(PointRequest pointRequest) {
    try {
      TriviaUser triviaUser = userRepository.findTriviaUserByName(pointRequest.name());

      double userPoints = triviaUser.getPoints();
      double updatedPoints = userPoints + pointRequest.extraPoints();

      triviaUser.setPoints(updatedPoints);
      userRepository.save(triviaUser);
      return true;

    } catch (Exception e) {
      logger.logError(e.toString());
      return false;
    }
  }
  public void addRoleFor(TriviaUser triviaUser, Role role) {
    TriviaUser triviaUser1 = userRepository.findTriviaUserByName(triviaUser.getName());
    Set<Role> oldRoles = triviaUser1.getRoles();

    Set<Role> copiedRoles = new HashSet<>(oldRoles);
    copiedRoles.add(role);

    userRepository.save(triviaUser1);
  }
}
