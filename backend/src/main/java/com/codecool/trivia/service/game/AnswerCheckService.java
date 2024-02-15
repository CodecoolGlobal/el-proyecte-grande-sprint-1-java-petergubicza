package com.codecool.trivia.service.game;

import com.codecool.trivia.dto.AnswerCheckDTO;
import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import javax.management.NotificationEmitter;
import java.util.UUID;

@Service
public class AnswerCheckService {
    private final AnswerRepository answerRepository;

    public AnswerCheckService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public AnswerCheckDTO isCorrect(UUID id) {
        Answer answer = answerRepository.findAnswerById(id);
        if (answer == null) {
            System.out.println("JÓ NEM LYO NEM JAO");
        }
        return new AnswerCheckDTO(answer.isIs_correct());
    }
}
