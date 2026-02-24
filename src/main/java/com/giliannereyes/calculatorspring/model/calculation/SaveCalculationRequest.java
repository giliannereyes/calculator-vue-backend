package com.giliannereyes.calculatorspring.model.calculation;

/**
 * Request payload for saving one calculation to the authenticated user's history.
 */
public class SaveCalculationRequest {
  private String expression;
  private Double result;

  /**
   * @return expression that was evaluated
   */
  public String getExpression() {
    return expression;
  }

  /**
   * Sets the expression value.
   *
   * @param expression expression that was evaluated
   */
  public void setExpression(String expression) {
    this.expression = expression;
  }

  /**
   * @return numeric result of the expression
   */
  public Double getResult() {
    return result;
  }

  /**
   * Sets the result value.
   *
   * @param result numeric result of the expression
   */
  public void setResult(Double result) {
    this.result = result;
  }
}
