package com.codecool.trivia.model.entity;

import com.codecool.trivia.model.enums.RoleName;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role")
public class Role {
  @Id
  @Enumerated(EnumType.STRING)
  @Column(unique = true)
  private RoleName role;
  @ManyToMany
  private List<TriviaUser> triviaUsers;

  public Role() {
  }

  public Role(RoleName role) {
    this.role = role;
  }

  public RoleName getRole() {
    return role;
  }

  public void setRole(RoleName role) {
    this.role = role;
  }

  public List<TriviaUser> getTriviaUsers() {
    return triviaUsers;
  }

  public void setTriviaUsers(List<TriviaUser> triviaUsers) {
    this.triviaUsers = triviaUsers;
  }

  public void add(TriviaUser element) {
    triviaUsers.add(element);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Role that = (Role) o;
    return getRole() == that.getRole();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getRole());
  }

  @Override
  public String toString() {
    return this.role.name();
  }
}
