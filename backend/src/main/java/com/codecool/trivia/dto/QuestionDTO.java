package com.codecool.trivia.dto;

public record QuestionDTO(int id, String question, String category, String difficulty, String[] answers) {
}
