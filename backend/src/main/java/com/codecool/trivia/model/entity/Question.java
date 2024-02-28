package com.codecool.trivia.model.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String questionDescription;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Difficulty difficulty;
    @OneToOne
    private Answer correctAnswer;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> incorrect_answers;
    public Question() {
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String question) {
        this.questionDescription = question;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correct_answer) {
        this.correctAnswer = correct_answer;
    }

    public List<Answer> getIncorrect_answers() {
        return incorrect_answers;
    }

    public void setIncorrect_answers(List<Answer> incorrect_answers) {
        this.incorrect_answers = incorrect_answers;
    }
}
