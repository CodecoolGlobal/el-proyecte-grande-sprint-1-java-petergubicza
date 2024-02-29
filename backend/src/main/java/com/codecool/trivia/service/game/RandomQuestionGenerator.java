package com.codecool.trivia.service.game;

import com.codecool.trivia.dto.frontend_request.quiz.AnswerDTO;
import com.codecool.trivia.exception.NotFoundQuestionException;
import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RandomQuestionGenerator {
    private final QuestionRepository questionRepository;

    @Autowired
    public RandomQuestionGenerator(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getRandomQuestion() {
        Optional<Question> randomQuestion = this.questionRepository.findRandomQuestion();
        if (randomQuestion.isPresent()) {
            return randomQuestion.get();
        } else {
            throw new NotFoundQuestionException();
        }
    }

    public List<AnswerDTO> getAnswersForCertainQuestion(Question question) {
        Optional<Question> optionalQuestion = this.questionRepository.findById(question.getId());
        if (optionalQuestion.isEmpty()) {
            throw new NotFoundQuestionException(question.getQuestionDescription());
        } else {
            List<Answer> answersForQuestion = optionalQuestion.get().getIncorrect_answers();
            answersForQuestion.add(optionalQuestion.get().getCorrectAnswer());

            List<AnswerDTO> answerDTOS = new ArrayList<>();

            for (Answer answer : answersForQuestion) {
                answerDTOS.add(new AnswerDTO(answer.getId(), answer.getDescription()));
            }

            return answerDTOS;
        }
    }
}
