package com.codecool.trivia.service.populate;

import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopulateUsers {
  private final static int NUMBER_OF_CHAMPIONS = 6;
  private final static String[] NAMES = {"Einstein", "Vágó István", "Sheldon", "Dumb", "Dumber", "TestUser"};
  private final static String[] PASSWORDS = {"pw1", "pw2", "pw3", "pw4", "pw5", "$2a$10$nCTiFgjQlHRB3mXCJ8Xd4.chwDQdLnGOUJt1r.FaGigOyyCoCWCg2"};
  private final static int[] POINTS = {200, 100, 50, 10, 5, 0};
  UserRepository userRepository;

  @Autowired
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
