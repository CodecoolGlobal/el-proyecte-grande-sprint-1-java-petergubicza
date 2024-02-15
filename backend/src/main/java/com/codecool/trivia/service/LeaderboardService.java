package com.codecool.trivia.service;

import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
  private final UserRepository userRepository;
  private final ConsoleLogger logger;

  public LeaderboardService(UserRepository userRepository, ConsoleLogger logger) {
    this.userRepository = userRepository;
    this.logger = logger;
  }

  public List<TriviaUser> findTopFiveUsers() {
    return userRepository.findTop5ByOrderByPointsDesc();
  }
}
