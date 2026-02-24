package com.giliannereyes.calculatorspring.repo;

import com.giliannereyes.calculatorspring.model.Calculation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for persisting and reading {@link Calculation} entities.
 */
public interface CalculationRepository extends JpaRepository<Calculation, Long> {

  /**
   * Returns paged calculations for one user ordered by most recent first.
   *
   * @param userId identifier of the user who owns the calculations
   * @param pageable paging and size configuration
   * @return page of calculations in descending creation-time order
   */
  Page<Calculation> findByUserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);
}
