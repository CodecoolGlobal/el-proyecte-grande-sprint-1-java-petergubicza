package com.codecool.trivia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TriviaReport(QuestionReport[] results) {
}
