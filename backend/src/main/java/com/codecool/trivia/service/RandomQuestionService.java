package com.codecool.trivia.service;

import com.codecool.trivia.dto.QuestionDTO;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.service.random_question.RandomQuestionGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RandomQuestionService {
    private final RandomQuestionGenerator randomQuestionGenerator;

    @Autowired
    public RandomQuestionService(RandomQuestionGenerator randomQuestionGenerator) {
        this.randomQuestionGenerator = randomQuestionGenerator;
    }
    /*public QuestionDTO getRandomQuestionDTO() {
        Question randomQuestion = this.randomQuestionGenerator.getRandomQuestion();
        List<> incorrectAnswers
        QuestionDTO randomQuestionDTO = new QuestionDTO(
                randomQuestion.getId().toString(),
                randomQuestion.getQuestion(),
                randomQuestion.getCategory().toString(),
                randomQuestion.getDifficulty().toString(),
                randomQuestion.getIncorrect_answers().toArray()
        );
        return null;
    }*/
}
