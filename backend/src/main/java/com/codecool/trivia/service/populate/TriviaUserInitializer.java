package com.codecool.trivia.service.populate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TriviaUserInitializer implements ApplicationRunner {
  private final PopulateUsers populateUsers;

  @Autowired
  public TriviaUserInitializer(PopulateUsers populateUsers) {
    this.populateUsers = populateUsers;
  }

  @Override
  public void run(ApplicationArguments args) {
    populateUsers.fillUsers();
  }
}