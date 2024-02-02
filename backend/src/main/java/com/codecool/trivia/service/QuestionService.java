package com.codecool.trivia.service;

import com.codecool.trivia.dao.QuestionDAOImpl;
import com.codecool.trivia.dto.QuestionDTO;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
  private final QuestionDAOImpl questionDAOImpl;

  public QuestionService(QuestionDAOImpl questionDAOImpl) {
    this.questionDAOImpl = questionDAOImpl;
  }

  public QuestionDTO getRandomQuestion() {
    return questionDAOImpl.getRandomQuestion();
  }
}
