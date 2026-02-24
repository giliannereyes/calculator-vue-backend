package com.giliannereyes.calculatorspring.model;

/**
 * Response DTO containing a computed calculator result.
 */
public class CalculationResponse {
  private double result;

  /**
   * Creates a response containing the evaluated result.
   *
   * @param result computed numeric result
   */
  public CalculationResponse(double result) {
    this.result = result;
  }

  /**
   * @return computed numeric result
   */
  public double getResult() {
    return result;
  }
}
