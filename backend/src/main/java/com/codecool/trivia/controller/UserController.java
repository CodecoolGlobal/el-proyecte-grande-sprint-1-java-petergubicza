package com.codecool.trivia.controller;

import com.codecool.trivia.dto.frontend_request.UserStatDTO;
import com.codecool.trivia.dto.frontend_request.PointRequestDTO;
import com.codecool.trivia.dto.frontend_request.UserRequestDTO;
import com.codecool.trivia.service.UserService;
import org.apache.tomcat.util.http.parser.Authorization;
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
  public ResponseEntity<String> addUser(@RequestBody UserRequestDTO userRequest) {
    if (userService.createUser(userRequest)) {
      return ResponseEntity.ok("User created successfully!");
    } else {
      return ResponseEntity.badRequest().body("Couldn't create user!");
    }
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> loginUser(@RequestBody UserRequestDTO userRequest) {
    return userService.login(userRequest);
  }

  @PatchMapping(value = "/addpoints")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<String> addPoints(@RequestBody PointRequestDTO pointRequest) {
    if (userService.addPointsToUser(pointRequest)) {
      return ResponseEntity.ok("Points added to user!");
    } else {
      return ResponseEntity.badRequest().body("Couldn't add points to user!");
    }
  }

  @GetMapping(value = "/stats")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<UserStatDTO> getUserStats(@RequestHeader ("Authorization") String authorization) {
    return userService.getUserStats(authorization);
  }
}
