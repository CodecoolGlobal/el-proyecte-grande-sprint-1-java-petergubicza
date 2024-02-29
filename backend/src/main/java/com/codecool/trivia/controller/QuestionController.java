package com.codecool.trivia.controller;

import com.codecool.trivia.dto.frontend_request.quiz.AnswerCheckDTO;
import com.codecool.trivia.dto.frontend_request.quiz.QuestionDTO;
import com.codecool.trivia.service.RandomQuestionService;
import com.codecool.trivia.service.game.AnswerCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {
    private final RandomQuestionService randomQuestionService;
    private final AnswerCheckService answerCheckService;

    @Autowired
    public QuestionController(RandomQuestionService randomQuestionService, AnswerCheckService answerCheckService) {
        this.randomQuestionService = randomQuestionService;
        this.answerCheckService = answerCheckService;
    }

    @GetMapping(value = "randomQuestion")
    public ResponseEntity<QuestionDTO> getRandomQuestion() {
        QuestionDTO randomQuestionDto = this.randomQuestionService.getRandomQuestionDTO();
        return ResponseEntity.ok(randomQuestionDto);
    }

    @GetMapping(value = "answerCheck")
    public ResponseEntity<AnswerCheckDTO> checkAnswer(@RequestParam UUID id) {
        AnswerCheckDTO answerCheckDTO = answerCheckService.isCorrect(id);
        return ResponseEntity.ok(answerCheckDTO);
    }
}
