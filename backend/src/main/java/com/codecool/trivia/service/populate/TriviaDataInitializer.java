package com.codecool.trivia.service.populate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TriviaDataInitializer implements ApplicationRunner {
  private static final int NUM_OF_QUESTIONS = 10;
  private final PopulateQuestions populateQuestions;

  @Autowired
  public TriviaDataInitializer(PopulateQuestions populateQuestions) {
    this.populateQuestions = populateQuestions;
  }

  @Override
  public void run(ApplicationArguments args) {
    populateQuestions.fillDatabaseWithQuestions(NUM_OF_QUESTIONS);
  }
}