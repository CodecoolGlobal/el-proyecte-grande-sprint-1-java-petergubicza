package com.codecool.trivia.service.game;

import com.codecool.trivia.dto.AnswerDTO;
import com.codecool.trivia.exception.NotFoundQuestionException;
import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class RandomQuestionGenerator {
    private final QuestionRepository questionRepository;
    private final Random random;

    @Autowired
    public RandomQuestionGenerator(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random();
    }

    private int getMaxAmountOfQuestionsFromDB() {
        return this.questionRepository.findAll()
                .size();
    }

    private int getRandomNumberInBound(int upperBound) {
        return this.random.nextInt(upperBound);
    }

    public Question getRandomQuestion() {
        int numberOfQuestions = getMaxAmountOfQuestionsFromDB();
        int validRandomNumber = getRandomNumberInBound(numberOfQuestions);
        List<Question> questions = this.questionRepository.findAll();
        return questions.get(validRandomNumber);
    }

    public List<AnswerDTO> getAnswersForCertainQuestion(Question question) {
        Optional<Question> optionalQuestion = this.questionRepository.findById(question.getId());
        if (optionalQuestion.isEmpty()) {
            throw new NotFoundQuestionException(question.getQuestionDescription());
        } else {
            List<Answer> answersForQuestion = optionalQuestion.get().getIncorrect_answers();
            answersForQuestion.add(optionalQuestion.get().getCorrect_answer());

            List<AnswerDTO> answerDTOS = new ArrayList<>();

            for (Answer answer : answersForQuestion) {
                answerDTOS.add(new AnswerDTO(answer.getId(), answer.getDescription()));
            }

            return answerDTOS;
        }
    }
}
