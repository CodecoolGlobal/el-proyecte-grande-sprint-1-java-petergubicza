package com.codecool.trivia.controller;

import com.codecool.trivia.dto.AnswerDTO;
import com.codecool.trivia.dto.QuestionDTO;
import com.codecool.trivia.service.AnswerService;
import com.codecool.trivia.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trivia")
public class TriviaController {
  private final QuestionService questionService;
  private final AnswerService answerService;

  @Autowired
  public TriviaController(QuestionService questionService, AnswerService answerService) {
    this.questionService = questionService;
    this.answerService = answerService;
  }

  @GetMapping("/randomQuestion")
  public QuestionDTO getRandomQuestion() {
    return questionService.getRandomQuestion();
  }

  @GetMapping("/answer/{id}")
  public AnswerDTO getCorrectAnswerById(@PathVariable int id) {
    return answerService.getCorrectAnswer(id);
  }
}
