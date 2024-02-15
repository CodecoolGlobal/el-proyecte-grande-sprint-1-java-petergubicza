package com.codecool.trivia.service;

import com.codecool.trivia.dto.AnswerDTO;
import com.codecool.trivia.dto.QuestionDTO;
import com.codecool.trivia.exception.NotFoundQuestionException;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.service.random_question.RandomQuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RandomQuestionService {
    private final RandomQuestionGenerator randomQuestionGenerator;

    @Autowired
    public RandomQuestionService(RandomQuestionGenerator randomQuestionGenerator) {
        this.randomQuestionGenerator = randomQuestionGenerator;
    }

    public QuestionDTO getRandomQuestionDTO() {
        Question randomQuestion = this.randomQuestionGenerator.getRandomQuestion();
        try {
            List<AnswerDTO> answers = this.randomQuestionGenerator.getAnswersForCertainQuestion(randomQuestion);
            return new QuestionDTO(
                    randomQuestion.getQuestionDescription(),
                    answers
            );
        } catch (NotFoundQuestionException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
