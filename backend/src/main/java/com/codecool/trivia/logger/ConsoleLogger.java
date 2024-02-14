package com.codecool.trivia.logger;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsoleLogger implements Logger {
  @Override
  public void logInfo(String message) {
    logMessage(message, "INFO");
  }

  @Override
  public void logError(String message) {
    logMessage(message, "ERROR");
  }

  private void logMessage(String message, String type) {
    String entry = "[" + LocalDateTime.now().withNano(0) + "] " + type + ": " + message;
    System.out.println(entry);
  }
}
