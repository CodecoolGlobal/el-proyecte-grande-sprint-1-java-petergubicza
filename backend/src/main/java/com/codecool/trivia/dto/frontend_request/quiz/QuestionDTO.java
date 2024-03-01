package com.codecool.trivia.dto.frontend_request.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public record QuestionDTO(String questionDescription, List<AnswerDTO> answers, UUID id) {

}
