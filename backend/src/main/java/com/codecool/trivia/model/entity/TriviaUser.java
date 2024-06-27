package com.codecool.trivia.model.entity;

import com.codecool.trivia.model.enums.RoleName;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class TriviaUser {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(unique = true)
  private String name;
  private String password;
  private int points;
  @ManyToMany
  private Set<Role> roles;

  public TriviaUser() {
  }

  public TriviaUser(String name, String password) {
    this.name = name;
    this.password = password;
    this.points = 0;
    this.roles = new HashSet<>(List.of(new Role(RoleName.ROLE_USER)));
  }

  public void addRole(Role role) {
    roles.add(role);
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
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

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TriviaUser that = (TriviaUser) o;
    return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getRoles(), that.getRoles());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getPassword(), getRoles());
  }
}
