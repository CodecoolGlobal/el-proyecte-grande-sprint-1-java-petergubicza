package com.codecool.trivia.repository;

import com.codecool.trivia.model.entity.TriviaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<TriviaUser, UUID> {
}
