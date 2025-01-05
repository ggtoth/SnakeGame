package com.ggoth.snakegamematchmaking.User;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UserRegisterRequest {
  private String username;
}
