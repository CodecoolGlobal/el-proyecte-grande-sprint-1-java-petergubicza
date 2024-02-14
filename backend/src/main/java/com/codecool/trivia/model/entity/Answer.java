package com.codecool.trivia.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String description;
  private boolean is_correct;
  @ManyToOne
  @JoinColumn(name = "question_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_answer_question"))
  private Question question;

  public Answer() {
  }

  public Answer(UUID id, String description, boolean is_correct, Question question) {
    this.id = id;
    this.description = description;
    this.is_correct = is_correct;
    this.question = question;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isIs_correct() {
    return is_correct;
  }

  public void setIs_correct(boolean is_correct) {
    this.is_correct = is_correct;
  }

  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
