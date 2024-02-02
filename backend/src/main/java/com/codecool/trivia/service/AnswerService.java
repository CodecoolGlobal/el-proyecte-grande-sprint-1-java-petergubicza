package com.codecool.trivia.service;

import com.codecool.trivia.dao.AnswerDAOImpl;
import com.codecool.trivia.dto.AnswerDTO;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
  private final AnswerDAOImpl answerDAO;

  public AnswerService(AnswerDAOImpl answerDAO) {
    this.answerDAO = answerDAO;
  }

  public AnswerDTO getCorrectAnswer(int id) {
    return new AnswerDTO(answerDAO.getCorrectAnswer(id));
  }
}
