package com.giliannereyes.calculatorspring.model;

/**
 * Request DTO carrying an expression to be evaluated by the calculator API.
 */
public class CalculationRequest {
  private String expression;

  /**
   * @return mathematical expression to evaluate
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Sets the expression to evaluate.
   *
   * @param expression mathematical expression in infix form
   */
  public void setExpression(String expression) {
    this.expression = expression;
  }
}

