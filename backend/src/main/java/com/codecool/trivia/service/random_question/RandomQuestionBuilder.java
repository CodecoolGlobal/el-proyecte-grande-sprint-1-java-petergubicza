package com.codecool.trivia.service.random_question;

import com.codecool.trivia.repository.AnswerRepository;
import com.codecool.trivia.repository.CategoryRepository;
import com.codecool.trivia.repository.DifficultyRepository;
import com.codecool.trivia.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomQuestionBuilder {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final CategoryRepository categoryRepository;
    private final DifficultyRepository difficultyRepository;
    private final Random random;

    @Autowired
    public RandomQuestionBuilder(QuestionRepository questionRepository,
                                 AnswerRepository answerRepository,
                                 CategoryRepository categoryRepository,
                                 DifficultyRepository difficultyRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.categoryRepository = categoryRepository;
        this.difficultyRepository = difficultyRepository;
        this.random = new Random();
    }
    private int getMaxAmountOfQuestionsFromDB() {
        return this.questionRepository.findAll()
                .size();
    }
}
