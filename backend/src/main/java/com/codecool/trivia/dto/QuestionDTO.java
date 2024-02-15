package com.codecool.trivia.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionDTO {
    private String questionDescription;
    private List<AnswerDTO> answers; // Map<Answer description, Answer id>

    public QuestionDTO(String questionDescription, List<AnswerDTO> answers) {
        this.questionDescription = questionDescription;
        this.answers = new ArrayList<>(answers);
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }
}
