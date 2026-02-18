package com.giliannereyes.calculatorspring.model;

import java.util.UUID;

public class User {
  private final UUID id;
  private String username;
  private String password;

  public User(String username, String password) {
    this.id = UUID.randomUUID();
    this.username = username;
    this.password = password;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }
}
