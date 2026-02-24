package com.giliannereyes.calculatorspring.model.auth;

/**
 * Response payload returned after login.
 */
public class LoginResponse {
  private final Long userId;
  private final String username;
  private final String token;

  /**
   * Creates a login response.
   *
   * @param userId authenticated user identifier
   * @param username authenticated username
   * @param token access token
   */
  public LoginResponse(Long userId, String username, String token) {
    this.userId = userId;
    this.username = username;
    this.token = token;
  }

  /**
   * @return authenticated user identifier
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * @return authenticated username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @return token string
   */
  public String getToken() {
    return token;
  }
}
