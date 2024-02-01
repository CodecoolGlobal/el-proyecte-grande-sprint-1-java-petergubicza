package com.codecool.trivia.service;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Service
public class DatabaseConnection {
  private static final String URL = "jdbc:postgresql://localhost:5432/trivia_test";
  private static final String USER = "postgres";
  private static final String PASSWORD = "Tralalajka121";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
