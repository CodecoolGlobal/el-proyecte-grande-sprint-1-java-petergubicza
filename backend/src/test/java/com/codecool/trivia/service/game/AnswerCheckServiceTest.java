package com.codecool.trivia.service.game;

import com.codecool.trivia.repository.AnswerRepository;
import com.codecool.trivia.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AnswerCheckServiceTest {
  @Mock
  private AnswerRepository answerRepository;
  @Mock
  private QuestionRepository questionRepository;

  @BeforeEach
  public void setup() {

  }

  @Test
  void getCorrectAnswer() {
    //GIVEN

    //WHEN

    //THEN

  }
}