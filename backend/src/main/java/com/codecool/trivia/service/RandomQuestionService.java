package com.codecool.trivia.service;

import com.codecool.trivia.repository.AnswerRepository;
import com.codecool.trivia.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RandomQuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final Random random;

    @Autowired
    public RandomQuestionService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.random = new Random();
    }
}
