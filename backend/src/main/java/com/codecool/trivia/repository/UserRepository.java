package com.codecool.trivia.repository;

import com.codecool.trivia.model.entity.TriviaUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<TriviaUser, UUID> {
  TriviaUser findTriviaUserByName(String triviaUserName);

  List<TriviaUser> findTop5ByOrderByPointsDesc();
}
