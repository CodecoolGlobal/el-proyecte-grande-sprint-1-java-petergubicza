package com.codecool.trivia.service;

import com.codecool.trivia.dto.LeaderboardDTO;
import com.codecool.trivia.logger.ConsoleLogger;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {
  private static final int LEADERBOARD_LENGTH = 5;
  private final UserRepository userRepository;
  private final ConsoleLogger logger;

  public LeaderboardService(UserRepository userRepository, ConsoleLogger logger) {
    this.userRepository = userRepository;
    this.logger = logger;
  }

  public LeaderboardDTO findTopFiveUsers() {
    List<TriviaUser> topPlayers = userRepository.findTop5ByOrderByPointsDesc();

    String[] players = new String[LEADERBOARD_LENGTH];
    double[] points = new double[LEADERBOARD_LENGTH];

    for (int i = 0; i < topPlayers.size(); i++) {
      players[i] = topPlayers.get(i).getName();
      points[i] = topPlayers.get(i).getPoints();
    }

    return new LeaderboardDTO(players, points);
  }
}
