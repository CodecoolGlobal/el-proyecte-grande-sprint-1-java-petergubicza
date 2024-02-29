package com.codecool.trivia.dto.frontend_request.leaderboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PointRequestDTO(@JsonProperty String name, @JsonProperty UUID questionId) {
}
