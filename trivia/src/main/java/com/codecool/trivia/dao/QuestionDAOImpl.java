package com.codecool.trivia.dao;

import com.codecool.trivia.dto.QuestionDTO;
import com.codecool.trivia.service.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Repository
public class QuestionDAOImpl implements QuestionDAO{
  @Override
  public QuestionDTO getRandomQuestion() {
    String query = "SELECT * FROM trivia_simple ORDER BY RANDOM() LIMIT 1";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        String question = resultSet.getString("question");
        ArrayList<String> orderedAnswers = new ArrayList<>(Arrays.asList(
                resultSet.getString("correct_answer"),
                resultSet.getString("first_wrong"),
                resultSet.getString("second_wrong"),
                resultSet.getString("third_wrong")
        ));
        int id = resultSet.getInt("question_id");

        String[] randomAnswers = shuffleAnswers(orderedAnswers);

        return new QuestionDTO(
                question,
                randomAnswers,
                id
        );
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }

  private String[] shuffleAnswers(ArrayList<String> orderedAnswers) {
    int numberOfQuestion = orderedAnswers.size();
    Collections.shuffle(orderedAnswers);

    String[] randomAnswers = new String[numberOfQuestion];

    for (int i = 0; i < numberOfQuestion; i++) {
      randomAnswers[i] = orderedAnswers.get(i);
    }

    return randomAnswers;
  }
}
