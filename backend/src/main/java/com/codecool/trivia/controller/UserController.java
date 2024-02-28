package com.codecool.trivia.controller;

import com.codecool.trivia.dto.frontend_request.PointRequestDTO;
import com.codecool.trivia.dto.frontend_request.UserRequestDTO;
import com.codecool.trivia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
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

  @PostMapping(value = "/signin")
  public ResponseEntity<?> loginUser(@RequestBody UserRequestDTO userRequest) {
    return userService.login(userRequest);
  }

  @PatchMapping(value = "/addpoints")
  public ResponseEntity<String> addPoints(@RequestBody PointRequestDTO pointRequest) {
    if (userService.addPointsToUser(pointRequest)) {
      return ResponseEntity.ok("Points added to user!");
    } else {
      return ResponseEntity.badRequest().body("Couldn't add points to user!");
    }
  }
}
