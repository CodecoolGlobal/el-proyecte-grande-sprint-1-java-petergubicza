package com.codecool.trivia.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String description;
  private boolean isCorrect;
  @ManyToOne
  @JoinColumn(name = "question_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_answer_question"))
  private Question question;

  public Answer() {
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isIsCorrect() {
    return isCorrect;
  }

  public void setIsCorrect(boolean is_correct) {
    this.isCorrect = is_correct;
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
