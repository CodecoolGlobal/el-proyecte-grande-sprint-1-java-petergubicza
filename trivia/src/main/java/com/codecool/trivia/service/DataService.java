package com.codecool.trivia.service;

import com.codecool.trivia.model.Trivia;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class DataService {
  public void insertData(Trivia trivia) {
    String query = "INSERT INTO trivia_simple(" +
            "category," +
            "question," +
            "correct_answer," +
            "first_wrong," +
            "second_wrong," +
            "third_wrong" +
            ") VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection()) {
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setString(1, trivia.category());
      preparedStatement.setString(2, trivia.question());
      preparedStatement.setString(3, trivia.correct_answer());
      preparedStatement.setString(4, trivia.first_wrong());
      preparedStatement.setString(5, trivia.second_wrong());
      preparedStatement.setString(6, trivia.third_wrong());

      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }
}
