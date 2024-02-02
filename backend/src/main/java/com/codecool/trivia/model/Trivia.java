package com.codecool.trivia.model;

public record Trivia(String category, String question, String correct_answer,
                     String first_wrong, String second_wrong, String third_wrong) {
}
