package com.codecool.trivia.dto.frontend_request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PointRequestDTO(@JsonProperty String name, @JsonProperty int extraPoints) {
}
