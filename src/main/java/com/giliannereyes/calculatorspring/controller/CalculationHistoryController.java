package com.giliannereyes.calculatorspring.controller;

import com.giliannereyes.calculatorspring.model.calculation.CalculationItemResponse;
import com.giliannereyes.calculatorspring.model.calculation.CalculationPageResponse;
import com.giliannereyes.calculatorspring.model.calculation.SaveCalculationRequest;
import com.giliannereyes.calculatorspring.service.CalculationPersistenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing endpoints for saving and reading calculation history.
 */
@RestController
@RequestMapping("/api/calculations")
@CrossOrigin(origins = "http://localhost:5173")
public class CalculationHistoryController {
  private final CalculationPersistenceService calculationPersistenceService;

  /**
   * Creates the calculation history controller.
   *
   * @param calculationPersistenceService service handling calculation persistence operations
   */
  public CalculationHistoryController(CalculationPersistenceService calculationPersistenceService) {
    this.calculationPersistenceService = calculationPersistenceService;
  }

  /**
   * Saves one calculated expression for the authenticated user.
   *
   * @param request expression and result payload
   * @param authentication authenticated principal carrying username
   * @return saved calculation item
   */
  @PostMapping
  public ResponseEntity<CalculationItemResponse> saveCalculation(@RequestBody SaveCalculationRequest request,
                                                                 Authentication authentication) {
    String expression = request.getExpression() == null ? "" : request.getExpression().trim();
    Double result = request.getResult();
    if (expression.isEmpty() || result == null) {
      return ResponseEntity.badRequest().build();
    }

    String username = String.valueOf(authentication.getPrincipal());
    CalculationItemResponse saved = calculationPersistenceService.saveForUser(username, expression, result);
    return ResponseEntity.ok(saved);
  }

  /**
   * Returns paged calculation history for the authenticated user.
   *
   * @param page zero-based page index
   * @param size page size
   * @param authentication authenticated principal carrying username
   * @return paged calculation history
   */
  @GetMapping
  public ResponseEntity<CalculationPageResponse> getHistory(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      Authentication authentication
  ) {
    int safePage = Math.max(page, 0);
    int safeSize = Math.min(Math.max(size, 1), 50);

    String username = String.valueOf(authentication.getPrincipal());
    CalculationPageResponse history = calculationPersistenceService.getHistoryForUser(username, safePage, safeSize);
    return ResponseEntity.ok(history);
  }
}
