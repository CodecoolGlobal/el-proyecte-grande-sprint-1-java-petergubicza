package com.codecool.trivia.controller;

import com.codecool.trivia.dto.frontend_request.quiz.QuestionDTO;
import com.codecool.trivia.service.game.RandomQuestionService;
import com.codecool.trivia.service.game.AnswerCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/question")
@EnableMethodSecurity
public class QuestionController {
  private final RandomQuestionService randomQuestionService;
  private final AnswerCheckService answerCheckService;

  @Autowired
  public QuestionController(RandomQuestionService randomQuestionService, AnswerCheckService answerCheckService) {
    this.randomQuestionService = randomQuestionService;
    this.answerCheckService = answerCheckService;
  }

  @GetMapping(value = "random_question")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<QuestionDTO> getRandomQuestion() {
    QuestionDTO randomQuestionDto = this.randomQuestionService.getRandomQuestionDTO();
    return ResponseEntity.ok(randomQuestionDto);
  }

  @GetMapping(value = "correct_answer")
  @PreAuthorize("hasRole('ROLE_USER')")
  public ResponseEntity<?> checkAnswer(@RequestParam UUID questionId) {
    return answerCheckService.getCorrectAnswer(questionId);
  }
}
