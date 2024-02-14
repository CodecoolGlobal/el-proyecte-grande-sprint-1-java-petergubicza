package com.codecool.trivia.service;

import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PopulateUsers {
  private final static int NUMBER_OF_CHAMPIONS = 5;
  private final static String[] NAMES = {"Einstein", "Vágó István", "Sheldon", "Dumb", "Dumber"};
  private final static String[] PASSWORDS = {"pw1", "pw2", "pw3", "pw4", "pw5"};
  private final static double[] POINTS = {200, 100, 50, 10, 5};
  UserRepository userRepository;

  public PopulateUsers(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void fillUsers() {
    for (int i = 0; i < NUMBER_OF_CHAMPIONS; i++) {
      TriviaUser newUser = new TriviaUser(
              NAMES[i],
              PASSWORDS[i]
      );
      newUser.setPoints(POINTS[i]);
      userRepository.save(newUser);
    }
  }
}
