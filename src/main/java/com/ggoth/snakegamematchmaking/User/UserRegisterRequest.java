package com.ggoth.snakegamematchmaking.User;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class UserRegisterRequest {
  private String username;

  public String getUsername() {
    return username;
  }
}
