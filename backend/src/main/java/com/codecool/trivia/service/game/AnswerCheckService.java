package com.codecool.trivia.service.game;

import com.codecool.trivia.dto.frontend_request.quiz.CorrectAnswerDTO;
import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.repository.AnswerRepository;
import com.codecool.trivia.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerCheckService {
  private final AnswerRepository answerRepository;
  private final QuestionRepository questionRepository;

  @Autowired
  public AnswerCheckService(AnswerRepository answerRepository, QuestionRepository questionRepository) {
    this.answerRepository = answerRepository;
    this.questionRepository = questionRepository;
  }

  public ResponseEntity<?> getCorrectAnswer(UUID questionId) {
    try {
      Question question = questionRepository.findById(questionId).get();

      Answer correctAnswer = answerRepository.findAnswersByQuestionAndIsCorrect(
              question,
              true
      );
      return ResponseEntity.ok().body(new CorrectAnswerDTO(correctAnswer.getId()));

    } catch (Exception e) {
      return ResponseEntity.badRequest().body("Coudn't find correct answer");
    }
  }
}
