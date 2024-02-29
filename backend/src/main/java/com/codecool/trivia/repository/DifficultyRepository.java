package com.codecool.trivia.repository;

import com.codecool.trivia.model.entity.Difficulty;
import com.codecool.trivia.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DifficultyRepository extends JpaRepository<Difficulty, UUID> {
  Optional<Difficulty> findDifficultyByName(String name);
  Difficulty findDifficultyByQuestions(Question question);
}
