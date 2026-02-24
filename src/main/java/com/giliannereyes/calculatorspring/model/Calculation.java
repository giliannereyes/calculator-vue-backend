package com.giliannereyes.calculatorspring.model;

import jakarta.persistence.*;
import java.time.Instant;

/**
 * JPA entity representing one saved calculation for a user.
 */
@Entity
@Table(name = "calculation")
public class Calculation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String expression;

  @Column(nullable = false)
  private double result;

  @Column(nullable = false)
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  /**
   * Protected no-args constructor required by JPA.
   */
  protected Calculation() {}

  /**
   * Creates a new calculation entity.
   *
   * @param expression expression that was evaluated
   * @param result computed result of the expression
   * @param user user who owns this calculation
   */
  public Calculation(String expression, double result, User user) {
    this.expression = expression;
    this.result = result;
    this.user = user;
    this.createdAt = Instant.now();
  }

  /**
   * @return database identifier of the calculation
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
   * @return numeric result of the expression
   */
  public double getResult() {
    return result;
  }

  /**
   * @return timestamp when the calculation was created
   */
  public Instant getCreatedAt() {
    return createdAt;
  }

  /**
   * @return user who owns this calculation
   */
  public User getUser() {
    return user;
  }
}
