package com.giliannereyes.calculatorspring.controller;

import com.giliannereyes.calculatorspring.model.CalculationRequest;
import com.giliannereyes.calculatorspring.model.CalculationResponse;
import com.giliannereyes.calculatorspring.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "http://localhost:5173")
public class CalculatorController {
  private static final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
  private final CalculatorService calculatorService;

  public CalculatorController(CalculatorService calculatorService) {
    this.calculatorService = calculatorService;
  }

  @PostMapping("/calculate")
  public CalculationResponse calculate(@RequestBody CalculationRequest request) {
    logger.info("Received expression: {}", request.getExpression());
    double result = calculatorService.calculate(request.getExpression());
    logger.info("Result: {}", result);
    return new CalculationResponse(result);
  }
}
