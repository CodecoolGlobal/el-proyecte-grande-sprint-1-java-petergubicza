package com.codecool.trivia.service.game;

import com.codecool.trivia.dto.frontend_request.quiz.AnswerCheckDTO;
import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerCheckService {
    private final AnswerRepository answerRepository;

    @Autowired
    public AnswerCheckService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public AnswerCheckDTO isCorrect(UUID id) {
        Answer answer = answerRepository.findAnswerById(id);
        if (answer == null) {
            System.out.println("JÓ NEM LYO NEM JAO");
        }
        return new AnswerCheckDTO(answer.isIsCorrect());
    }
}
