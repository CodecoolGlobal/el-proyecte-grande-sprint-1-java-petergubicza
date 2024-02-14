package com.codecool.trivia.controller;

import com.codecool.trivia.service.populate.PopulateQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {
  private final PopulateQuestions populateDatabase;

  @Autowired
  public TriviaController(PopulateQuestions populateDatabase) {
    this.populateDatabase = populateDatabase;
  }

  @GetMapping("/test")
  public void fetchQuestion(@RequestParam int quantity) {
    populateDatabase.fillDatabaseWithQuestions(quantity);
  }
}
