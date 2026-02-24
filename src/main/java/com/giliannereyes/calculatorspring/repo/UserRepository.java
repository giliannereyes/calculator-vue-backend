package com.giliannereyes.calculatorspring.repo;

import com.giliannereyes.calculatorspring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for performing CRUD and query operations on {@link User} entities.
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Finds a user by username.
   *
   * @param username unique username to search for
   * @return matching user if present, otherwise empty
   */
  Optional<User> findByUsername(String username);
}
