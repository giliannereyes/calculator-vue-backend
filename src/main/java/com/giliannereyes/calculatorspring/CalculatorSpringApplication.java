package com.giliannereyes.calculatorspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the calculator Spring Boot backend.
 */
@SpringBootApplication
public class CalculatorSpringApplication {

  /**
   * Boots the Spring application context.
   *
   * @param args command-line arguments passed to the JVM process
   */
  public static void main(String[] args) {
    SpringApplication.run(CalculatorSpringApplication.class, args);
  }

}
