package com.codecool.trivia.service;

import com.codecool.trivia.dto.frontend_request.AnswerDTO;
import com.codecool.trivia.dto.frontend_request.QuestionDTO;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.service.game.RandomQuestionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class RandomQuestionServiceTest {
    @Mock
    private RandomQuestionGenerator randomQuestionGenerator;
    private RandomQuestionService randomQuestionService;
    @BeforeEach
    public void setup() {
        this.randomQuestionService = new RandomQuestionService(randomQuestionGenerator);
    }

    @Test
    void whenGetRandomQuestionDTOThenRandomQuestionDTO() {
        //ARRANGE
        Question testQuestion = new Question();
        testQuestion.setQuestionDescription("asd");
        AnswerDTO testAnswerDto = new AnswerDTO(UUID.randomUUID(), "asd");
        when(randomQuestionGenerator.getRandomQuestion()).thenReturn(testQuestion);
        when(randomQuestionGenerator.getAnswersForCertainQuestion(testQuestion)).thenReturn(List.of(testAnswerDto));
        QuestionDTO expected = new QuestionDTO("asd", List.of(testAnswerDto));

        //ACT
        QuestionDTO actual = this.randomQuestionService.getRandomQuestionDTO();

        //ASSERT
        assertEquals(expected, actual);
    }
}