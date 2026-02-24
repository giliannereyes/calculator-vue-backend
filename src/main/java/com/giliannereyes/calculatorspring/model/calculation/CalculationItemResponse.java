package com.giliannereyes.calculatorspring.model.calculation;

import java.time.Instant;

/**
 * Response item for one stored calculation.
 */
public class CalculationItemResponse {
  private final Long id;
  private final String expression;
  private final double result;
  private final Instant createdAt;

  /**
   * Creates a calculation item response.
   *
   * @param id calculation identifier
   * @param expression expression that was evaluated
   * @param result evaluated result
   * @param createdAt creation timestamp
   */
  public CalculationItemResponse(Long id, String expression, double result, Instant createdAt) {
    this.id = id;
    this.expression = expression;
    this.result = result;
    this.createdAt = createdAt;
  }

  /**
   * @return calculation identifier
   */
  public Long getId() {
    return id;
  }

  /**
   * @return expression that was evaluated
   */
  public String getExpression() {
    return expression;
  }

  /**
   * @return calculated result
   */
  public double getResult() {
    return result;
  }

  /**
   * @return calculation timestamp
   */
  public Instant getCreatedAt() {
    return createdAt;
  }
}
