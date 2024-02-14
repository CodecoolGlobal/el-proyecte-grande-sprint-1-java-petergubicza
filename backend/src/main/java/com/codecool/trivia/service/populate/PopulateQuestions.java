package com.codecool.trivia.service.populate;

import com.codecool.trivia.model.entity.Answer;
import com.codecool.trivia.model.entity.Category;
import com.codecool.trivia.model.entity.Difficulty;
import com.codecool.trivia.model.entity.Question;
import com.codecool.trivia.model.report.Trivia;
import com.codecool.trivia.model.report.TriviaReport;
import com.codecool.trivia.repository.AnswerRepository;
import com.codecool.trivia.repository.CategoryRepository;
import com.codecool.trivia.repository.DifficultyRepository;
import com.codecool.trivia.repository.QuestionRepository;
import com.codecool.trivia.service.TriviaAPIService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PopulateQuestions {
  private final TriviaAPIService triviaAPIService;
  private final QuestionRepository questionRepository;
  private final DifficultyRepository difficultyRepository;
  private final CategoryRepository categoryRepository;
  private final AnswerRepository answerRepository;

  public PopulateQuestions(TriviaAPIService triviaAPIService, QuestionRepository questionRepository, DifficultyRepository difficultyRepository, CategoryRepository categoryRepository, AnswerRepository answerRepository) {
    this.triviaAPIService = triviaAPIService;
    this.questionRepository = questionRepository;
    this.difficultyRepository = difficultyRepository;
    this.categoryRepository = categoryRepository;
    this.answerRepository = answerRepository;
  }

  public void fillDatabaseWithQuestions(int numberOfQuestions) {
    TriviaReport questions = triviaAPIService.fetchTriviaQuestions(numberOfQuestions);

    for (Trivia question : questions.results()) {
      makeDatabaseEntry(question);
    }
  }

  private void makeDatabaseEntry(Trivia question) {
    Question newQuestion = new Question();

    newQuestion.setQuestion(question.question());
    newQuestion.setCategory(checkQuestionCategory(question.category()));
    newQuestion.setDifficulty(checkQuestionDifficulty(question.difficulty()));

    questionRepository.save(newQuestion);

    newQuestion.setCorrect_answer(createAnswer(newQuestion, question.correct_answer(), true));
    newQuestion.setIncorrect_answers(collectIncorrectAnswers(newQuestion, question.incorrect_answers()));

    questionRepository.save(newQuestion);
  }

  private List<Answer> collectIncorrectAnswers(Question questionEntity, String[] incorrectAnswers) {
    List<Answer> newIncorrectAnswers = new ArrayList<>();

    for (String incorrectAnswer : incorrectAnswers) {
      newIncorrectAnswers.add(createAnswer(questionEntity, incorrectAnswer, false));
    }

    return newIncorrectAnswers;
  }

  private Answer createAnswer(Question questionEntity, String answer, boolean isCorrect) {
    Answer newAnswer = new Answer();
    newAnswer.setDescription(answer);
    newAnswer.setIs_correct(isCorrect);
    newAnswer.setQuestion(questionEntity);
    answerRepository.save(newAnswer);

    return newAnswer;
  }

  private Difficulty checkQuestionDifficulty(String difficultyName) {
    Optional<Difficulty> difficulty = difficultyRepository.findDifficultyByName(difficultyName);

    if (difficulty.isPresent()) {
      return difficulty.get();
    } else {
      Difficulty newDifficulty = new Difficulty();
      newDifficulty.setName(difficultyName);
      difficultyRepository.save(newDifficulty);

      return newDifficulty;
    }

  }

  private Category checkQuestionCategory(String categoryName) {
    Optional<Category> category = categoryRepository.findCategoryByName(categoryName);

    if (category.isPresent()) {
      return category.get();
    } else {
      Category newCategory = new Category();
      newCategory.setName(categoryName);
      categoryRepository.save(newCategory);

      return newCategory;
    }
  }


//  private Difficulty getDifficulty(Trivia question) {
//    Optional<Difficulty> difficulty = checkIfDifficultyExists(question.difficulty());
//
//    if (difficulty.isPresent()) {
//      return difficulty.get();
//    }
//
//    return saveDifficulty(question.difficulty());
//  }
//
//  private Difficulty saveDifficulty(String difficulty) {
//    Difficulty newDifficulty = new Difficulty();
//    newDifficulty.setName(difficulty);
//
//    difficultyRepository.save(newDifficulty);
//
//    return newDifficulty;
//  }
//
//  private Optional<Difficulty> checkIfDifficultyExists(String difficultyName) {
//    return difficultyRepository.getDifficultyByName(difficultyName);
//  }

}
