package com.codecool.trivia.model.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record QuestionReport(String category, String difficulty, String question) {
}
