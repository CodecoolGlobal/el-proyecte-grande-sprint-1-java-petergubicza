package com.codecool.trivia.model.request_schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PointRequest(@JsonProperty String name, @JsonProperty int extraPoints) {
}
