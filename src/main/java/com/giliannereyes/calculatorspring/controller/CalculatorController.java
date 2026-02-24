package com.giliannereyes.calculatorspring.controller;

import com.giliannereyes.calculatorspring.model.CalculationRequest;
import com.giliannereyes.calculatorspring.model.CalculationResponse;
import com.giliannereyes.calculatorspring.service.CalculatorService;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller exposing calculator operations.
 */
@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {
  private final CalculatorService calculatorService;

  /**
   * Creates a controller with the calculator business service.
   *
   * @param calculatorService service responsible for expression evaluation
   */
  public CalculatorController(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  /**
   * Evaluates a mathematical expression and returns the numeric result.
   *
   * @param request payload containing the expression to evaluate
   * @return response object containing the computed result
   */
  @PostMapping("/calculate")
  public CalculationResponse calculate(@RequestBody CalculationRequest request) {
    double result = calculatorService.calculate(request.getExpression());
    return new CalculationResponse(result);
  }
}
