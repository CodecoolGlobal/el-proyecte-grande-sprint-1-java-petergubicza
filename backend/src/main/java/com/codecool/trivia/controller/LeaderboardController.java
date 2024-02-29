package com.codecool.trivia.controller;

import com.codecool.trivia.dto.frontend_request.leaderboard.LeaderboardDTO;
import com.codecool.trivia.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/leaderboard")
@EnableMethodSecurity
public class LeaderboardController {
  private static final int LEADERBOARD_LENGTH = 5;
  private final LeaderboardService leaderboardService;

  @Autowired
  public LeaderboardController(LeaderboardService leaderboardService) {
    this.leaderboardService = leaderboardService;
  }

  @GetMapping(value = "/leaderboard")
  @PreAuthorize("hasRole('ROLE_USER')")
  public LeaderboardDTO fetchTopFivePlayers() {
    return leaderboardService.findTopFiveUsers();
  }
}
