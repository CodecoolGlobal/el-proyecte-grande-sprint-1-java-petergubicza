package com.codecool.trivia.dto;

public record QuestionDTO(String id, String question, String category, String difficulty, String[] answers) {
}
