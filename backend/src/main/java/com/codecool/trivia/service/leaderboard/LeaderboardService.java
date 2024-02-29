package com.codecool.trivia.service.leaderboard;

import com.codecool.trivia.dto.frontend_request.leaderboard.LeaderboardDTO;
import com.codecool.trivia.dto.frontend_request.leaderboard.UserScore;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {
  private static final int LEADERBOARD_LENGTH = 5;
  private final UserRepository userRepository;

  @Autowired
  public LeaderboardService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LeaderboardDTO findTopFiveUsers() {
    List<TriviaUser> topPlayers = userRepository.findTop5ByOrderByPointsDesc();

    UserScore[] leaderboard = new UserScore[topPlayers.size()];

    for (int i = 0; i < topPlayers.size(); i++) {
      leaderboard[i] = new UserScore(topPlayers.get(i).getName(), topPlayers.get(i).getPoints());
    }

    return new LeaderboardDTO(leaderboard);
  }
}
