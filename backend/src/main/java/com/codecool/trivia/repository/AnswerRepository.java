package com.codecool.trivia.repository;

import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    Answer findAnswersByQuestionAndIsCorrect(Question question, boolean isCorrect);
}
