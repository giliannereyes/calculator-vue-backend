package com.giliannereyes.calculatorspring.model;

import java.util.UUID;

public class Calculation {
  private UUID id;
  private String expression;
  private double result;
  private UUID userId;

  public Calculation(String expression, double result, UUID userId) {
    this.id = UUID.randomUUID();
    this.expression = expression;
    this.result = result;
    this.userId = userId;
  }

  public UUID getId() {
    return id;
  }

  public String getExpression() {
    return expression;
  }

  public double getResult() {
    return result;
  }

  public UUID getUserId() {
    return userId;
  }
}
