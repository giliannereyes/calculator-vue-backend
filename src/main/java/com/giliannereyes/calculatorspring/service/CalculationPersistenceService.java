package com.giliannereyes.calculatorspring.service;

import com.giliannereyes.calculatorspring.model.Calculation;
import com.giliannereyes.calculatorspring.model.User;
import com.giliannereyes.calculatorspring.model.calculation.CalculationItemResponse;
import com.giliannereyes.calculatorspring.model.calculation.CalculationPageResponse;
import com.giliannereyes.calculatorspring.repo.CalculationRepository;
import com.giliannereyes.calculatorspring.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * Service for persisting and retrieving user-specific calculation history.
 */
@Service
public class CalculationPersistenceService {
  private final UserRepository userRepository;
  private final CalculationRepository calculationRepository;

  /**
   * Creates a calculation persistence service.
   *
   * @param userRepository repository for user lookups
   * @param calculationRepository repository for calculation persistence
   */
  public CalculationPersistenceService(UserRepository userRepository, CalculationRepository calculationRepository) {
    this.userRepository = userRepository;
    this.calculationRepository = calculationRepository;
  }

  /**
   * Saves one calculation for the provided username.
   *
   * @param username owner username
   * @param expression expression that was evaluated
   * @param result expression result
   * @return saved calculation as response DTO
   */
  public CalculationItemResponse saveForUser(String username, String expression, double result) {
    User user = findUserByUsername(username);
    Calculation saved = calculationRepository.save(new Calculation(expression, result, user));
    return toItemResponse(saved);
  }

  /**
   * Returns paged history for the provided username.
   *
   * @param username owner username
   * @param page zero-based page index
   * @param size page size
   * @return paged calculation response
   */
  public CalculationPageResponse getHistoryForUser(String username, int page, int size) {
    User user = findUserByUsername(username);
    Page<Calculation> historyPage = calculationRepository.findByUserIdOrderByCreatedAtDesc(
        user.getId(),
        PageRequest.of(page, size)
    );

    return new CalculationPageResponse(
        historyPage.getContent().stream().map(this::toItemResponse).toList(),
        historyPage.getNumber(),
        historyPage.getSize(),
        historyPage.getTotalElements(),
        historyPage.getTotalPages()
    );
  }

  private User findUserByUsername(String username) {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new IllegalArgumentException("Authenticated user does not exist."));
  }

  private CalculationItemResponse toItemResponse(Calculation calculation) {
    return new CalculationItemResponse(
        calculation.getId(),
        calculation.getExpression(),
        calculation.getResult(),
        calculation.getCreatedAt()
    );
  }
}
