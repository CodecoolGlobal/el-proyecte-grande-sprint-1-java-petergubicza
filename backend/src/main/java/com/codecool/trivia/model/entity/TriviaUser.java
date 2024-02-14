package com.codecool.trivia.model.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class TriviaUser {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(unique = true)
  private String name;
  private String password;
  private double points;

  public TriviaUser() {
  }

  public TriviaUser(String name, String password) {
    this.name = name;
    this.password = password;
    this.points = 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public double getPoints() {
    return points;
  }

  public void setPoints(double points) {
    this.points = points;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
