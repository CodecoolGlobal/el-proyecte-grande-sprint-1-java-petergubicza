package com.codecool.trivia.model.report;

public record Trivia(String difficulty, String category, String question,
                     String correct_answer, String[] incorrect_answers) {
}
