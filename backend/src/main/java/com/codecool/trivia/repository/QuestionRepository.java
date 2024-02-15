package com.codecool.trivia.repository;

import com.codecool.trivia.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findAll();
    Optional<Question> findById(UUID uuid);
}
