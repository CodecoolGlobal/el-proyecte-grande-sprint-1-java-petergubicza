package com.codecool.trivia.dao;

import com.codecool.trivia.service.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AnswerDAOImpl implements AnswerDAO{
  @Override
  public String getCorrectAnswer(int id) {
    String query = "SELECT correct_answer FROM trivia_simple WHERE question_id=?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query)) {

      preparedStatement.setInt(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        return resultSet.getString("correct_answer");
      }

    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }
}
