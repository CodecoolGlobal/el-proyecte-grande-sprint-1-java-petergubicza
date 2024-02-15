package com.codecool.trivia.controller;

import com.codecool.trivia.dto.QuestionDTO;
import com.codecool.trivia.service.RandomQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/trivia/")
public class QuestionController {
    private final RandomQuestionService randomQuestionService;

    @Autowired
    public QuestionController(RandomQuestionService randomQuestionService) {
        this.randomQuestionService = randomQuestionService;
    }

    @GetMapping(value = "randomQuestion")
    public ResponseEntity<QuestionDTO> getRandomQuestion() {
        return null;
    }
}
