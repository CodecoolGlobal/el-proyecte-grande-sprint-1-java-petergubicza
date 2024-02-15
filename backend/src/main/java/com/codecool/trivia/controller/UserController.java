package com.codecool.trivia.controller;

import com.codecool.trivia.model.request_schema.PointRequest;
import com.codecool.trivia.model.request_schema.UserRequest;
import com.codecool.trivia.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/trivia")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping(value = "/adduser")
  public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest) {
    if (userService.createUser(userRequest)) {
      return ResponseEntity.ok("User created successfully!");
    } else {
      return ResponseEntity.badRequest().body("Couldn't create user!");
    }
  }

  @PatchMapping(value = "/addpoints")
  public ResponseEntity<String> addPoints(@RequestBody PointRequest pointRequest) {
    if (userService.addPointsToUser(pointRequest)) {
      return ResponseEntity.ok("Points added to user!");
    } else {
      return ResponseEntity.badRequest().body("Couldn't add points to user!");
    }
  }
}
