package com.giliannereyes.calculatorspring.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity representing an application user.
 */
@Entity
@Table(name = "app_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Calculation> calculations = new ArrayList<>();

  /**
   * Protected no-args constructor required by JPA.
   */
  protected User() {}

  /**
   * Creates a new user entity.
   *
   * @param username user's unique login name
   * @param password user's password value
   */
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  /**
   * @return database identifier of the user
   */
  public Long getId() {
    return id;
  }

  /**
   * @return user's unique username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @return user's password value
   */
  public String getPassword() {
    return password;
  }

  /**
   * @return calculations associated with this user
   */
  public List<Calculation> getCalculations() {
    return calculations;
  }
}
