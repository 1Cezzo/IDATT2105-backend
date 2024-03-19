package com.IDATT2105.IDATT2105.model;

public class CalculationRequest {
  private String equation;
  private User user;

  public CalculationRequest(String equation, User user) {
    this.equation = equation;
    this.user = user;
  }

  public String getEquation() {
    return equation;
  }

  public void setEquation(String equation) {
    this.equation = equation;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
