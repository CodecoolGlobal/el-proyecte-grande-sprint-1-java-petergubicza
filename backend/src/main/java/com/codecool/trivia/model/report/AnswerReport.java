package com.codecool.trivia.model.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AnswerReport(String correct_answer, String[] incorrect_answers) {
}
