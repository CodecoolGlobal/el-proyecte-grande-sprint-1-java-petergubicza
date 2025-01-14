package com.codecool.trivia.exception;

public class NotFoundQuestionException extends RuntimeException {
    public NotFoundQuestionException(String question) {
        super(String.format("%s question not found", question));
    }

    public NotFoundQuestionException() {
        super("Question not found in database");
    }
}
