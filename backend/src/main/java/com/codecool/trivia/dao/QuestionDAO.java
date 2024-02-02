package com.codecool.trivia.dao;

import com.codecool.trivia.dto.QuestionDTO;

public interface QuestionDAO {
  QuestionDTO getRandomQuestion();
}
