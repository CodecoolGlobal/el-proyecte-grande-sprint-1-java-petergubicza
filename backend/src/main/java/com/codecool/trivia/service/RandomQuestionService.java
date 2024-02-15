package com.codecool.trivia.service;

import com.codecool.trivia.repository.AnswerRepository;
import com.codecool.trivia.repository.QuestionRepository;
import com.codecool.trivia.service.random_question.RandomQuestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomQuestionService {
    private final RandomQuestionBuilder randomQuestionBuilder;

    @Autowired
    public RandomQuestionService(RandomQuestionBuilder randomQuestionBuilder) {
        this.randomQuestionBuilder = randomQuestionBuilder;
    }
}
