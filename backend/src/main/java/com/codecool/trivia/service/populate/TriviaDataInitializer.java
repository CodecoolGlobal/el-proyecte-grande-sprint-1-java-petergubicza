package com.codecool.trivia.service.populate;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TriviaDataInitializer implements ApplicationRunner {
    private static final int NUM_OF_QUESTIONS = 10;
    private final PopulateQuestions populateQuestions;

    public TriviaDataInitializer(PopulateQuestions populateQuestions) {
        this.populateQuestions = populateQuestions;
    }

    @Override
    public void run(ApplicationArguments args) {
        populateQuestions.fillDatabaseWithQuestions(NUM_OF_QUESTIONS);
    }
}