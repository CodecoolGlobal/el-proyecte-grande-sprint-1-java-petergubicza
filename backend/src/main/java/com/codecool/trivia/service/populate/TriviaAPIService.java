package com.codecool.trivia.service.populate;

import com.codecool.trivia.model.report.Trivia;
import com.codecool.trivia.model.report.TriviaReport;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TriviaAPIService {
  private final WebClient webClient;

  @Autowired
  public TriviaAPIService(WebClient webClient) {
    this.webClient = webClient;
  }

  public TriviaReport fetchTriviaQuestions(int numberOfTrivia) {
    String url = String.format("https://opentdb.com/api.php?amount=%s&type=multiple", numberOfTrivia);

    TriviaReport triviaReport = webClient.get()
            .uri(url)
            .retrieve()
            .bodyToMono(TriviaReport.class)
            .block();

    decodeResponse(triviaReport);

    return triviaReport;
  }

  private void decodeResponse(TriviaReport triviaReport) { // decode Html characteristics
    if (triviaReport != null && triviaReport.results() != null) {
      for (Trivia question : triviaReport.results()) {

        question.setQuestion(StringEscapeUtils.unescapeHtml4(question.getQuestion()));
        question.setCorrect_answer(StringEscapeUtils.unescapeHtml4(question.getCorrect_answer()));

        String[] incorrectAnswers = question.getIncorrect_answers();

        for (int i = 0; i < incorrectAnswers.length; i++) {
          incorrectAnswers[i] = StringEscapeUtils.unescapeHtml4(incorrectAnswers[i]);
        }
      }
    }
  }
}