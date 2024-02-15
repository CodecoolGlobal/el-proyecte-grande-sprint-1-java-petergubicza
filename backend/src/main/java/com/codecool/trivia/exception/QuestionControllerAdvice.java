package com.codecool.trivia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class QuestionControllerAdvice {
    @ResponseBody
    @ExceptionHandler(NotFoundQuestionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String notFoundQuestionExceptionHandler(NotFoundQuestionException e) {
        return e.getMessage();
    }
}
