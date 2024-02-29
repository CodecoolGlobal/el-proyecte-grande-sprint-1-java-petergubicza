package com.codecool.trivia.controller;

import com.codecool.trivia.dto.frontend_request.leaderboard.PointRequestDTO;
import com.codecool.trivia.dto.frontend_request.user.NewRoleDTO;
import com.codecool.trivia.dto.frontend_request.user.UserNamePasswordDTO;
import com.codecool.trivia.dto.frontend_request.user.UserRolesDTO;
import com.codecool.trivia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
@EnableMethodSecurity
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/register")
  public ResponseEntity<?> addUser(@RequestBody UserNamePasswordDTO userRequest) {
    return userService.createUser(userRequest);
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> loginUser(@RequestBody UserNamePasswordDTO userRequest) {
    return userService.login(userRequest);
  }

  @PatchMapping(value = "/addpoints")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<?> addPoints(@RequestBody PointRequestDTO pointRequest) {
    return userService.addPointsToUser(pointRequest);
  }

  @GetMapping(value = "/stats")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<?> getUserStats(@RequestHeader ("Authorization") String authorization) {
    return userService.getUserStats(authorization);
  }

  @PatchMapping(value = "/addrole")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<?> addRoleForUser(@RequestBody NewRoleDTO newRoleForUser) {
    return userService.addRoleFor(newRoleForUser);
  }
}
