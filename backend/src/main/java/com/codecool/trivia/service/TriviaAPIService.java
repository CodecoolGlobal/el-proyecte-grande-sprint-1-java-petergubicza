package com.codecool.trivia.service;

import com.codecool.trivia.model.TriviaReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TriviaAPIService {
  private final RestTemplate restTemplate;

  public TriviaAPIService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public TriviaReport[] fetchTriviaQuestions(int numberOfTrivia) {

    String url = String.format("https://opentdb.com/api.php?amount=%s&difficulty=easy&type=multiple", numberOfTrivia);

    return restTemplate.getForObject(url, TriviaReport[].class);
  }
}
