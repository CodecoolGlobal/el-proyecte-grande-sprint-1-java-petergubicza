package com.codecool.trivia.dto.external_api_response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TriviaApiResponseDTO(TriviaDTO[] results) {
}
