package com.codecool.trivia.model.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TriviaReport(Trivia[] results) {
}
