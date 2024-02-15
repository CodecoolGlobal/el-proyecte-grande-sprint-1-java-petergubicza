package com.codecool.trivia.service;

import com.codecool.trivia.model.report.TriviaReport;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class TriviaAPIService {
  private final WebClient webClient;

  public TriviaAPIService(WebClient webClient) {
    this.webClient = webClient;
  }

  public TriviaReport fetchTriviaQuestions(int numberOfTrivia) {

    String url = String.format("https://opentdb.com/api.php?amount=%s&type=multiple", numberOfTrivia);

    return webClient
            .get()
            .uri(url)
            .retrieve()
            .bodyToMono(TriviaReport.class)
            .block();
  }
}
