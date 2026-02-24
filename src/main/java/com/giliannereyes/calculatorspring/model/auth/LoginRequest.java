package com.giliannereyes.calculatorspring.model.auth;

/**
 * Request payload for user login.
 */
public class LoginRequest {
  private String username;
  private String password;

  /**
   * @return submitted username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username submitted username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return submitted password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password submitted password
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
