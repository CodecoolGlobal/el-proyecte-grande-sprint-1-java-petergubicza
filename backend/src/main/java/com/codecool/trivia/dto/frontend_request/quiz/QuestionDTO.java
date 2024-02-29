package com.codecool.trivia.dto.frontend_request.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class QuestionDTO {
    private UUID id;
    private String questionDescription;
    private List<AnswerDTO> answers; // Map<Answer description, Answer id>

    public QuestionDTO(UUID id, String questionDescription, List<AnswerDTO> answers) {
        this.id = id;
        this.questionDescription = questionDescription;
        this.answers = new ArrayList<>(answers);
    }

    public UUID getId() {
        return id;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO that = (QuestionDTO) o;
        return Objects.equals(questionDescription, that.questionDescription) && Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionDescription, answers);
    }
}
