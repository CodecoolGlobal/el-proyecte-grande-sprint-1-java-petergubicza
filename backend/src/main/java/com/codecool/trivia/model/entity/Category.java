package com.codecool.trivia.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(unique = true)
  private String name;
  @OneToMany(mappedBy = "category")
  private List<Question> questions;

  public Category() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
