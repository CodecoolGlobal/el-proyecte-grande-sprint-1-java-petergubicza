package com.codecool.trivia.service.game;

import com.codecool.trivia.dto.frontend_request.quiz.AnswerDTO;
import com.codecool.trivia.dto.frontend_request.quiz.QuestionDTO;
import com.codecool.trivia.exception.NotFoundQuestionException;
import com.codecool.trivia.model.entity.Question;
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
                    answers,
                    randomQuestion.getId());
        } catch (NotFoundQuestionException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
