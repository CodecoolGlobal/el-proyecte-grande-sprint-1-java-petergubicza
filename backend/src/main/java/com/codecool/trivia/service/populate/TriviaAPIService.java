package com.codecool.trivia.service.populate;

import com.codecool.trivia.model.report.Trivia;
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

        TriviaReport triviaReport = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(TriviaReport.class)
                .block();

        if (triviaReport != null && triviaReport.results() != null) {
            for (Trivia question : triviaReport.results()) {
                question.setQuestion(StringEscapeUtils.unescapeHtml4(question.getQuestion()));
                question.setCorrect_answer(StringEscapeUtils.unescapeHtml4(question.getCorrect_answer()));
                for (int i = 0; i < question.getIncorrect_answers().size(); i++) {
                    String escapedAnswer = question.getIncorrect_answers().get(i);
                    question.getIncorrect_answers().set(i, StringEscapeUtils.unescapeHtml4(escapedAnswer));
                }
            }

        return triviaReport;
    }
}
