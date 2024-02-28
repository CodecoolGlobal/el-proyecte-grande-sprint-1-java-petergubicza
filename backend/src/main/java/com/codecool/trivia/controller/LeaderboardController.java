package com.codecool.trivia.controller;

import com.codecool.trivia.dto.LeaderboardDTO;
import com.codecool.trivia.model.entity.TriviaUser;
import com.codecool.trivia.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/leaderboard")
public class LeaderboardController {
  private static final int LEADERBOARD_LENGTH = 5;
  private final LeaderboardService leaderboardService;

  @Autowired
  public LeaderboardController(LeaderboardService leaderboardService) {
    this.leaderboardService = leaderboardService;
  }

  @GetMapping(value = "/leaderboard")
  public LeaderboardDTO fetchTopFivePlayers() {
    return leaderboardService.findTopFiveUsers();
  }
}
