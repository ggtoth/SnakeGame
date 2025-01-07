package com.ggoth.snakegamematchmaking.user;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserLobbyId implements Serializable {

  @Column(name = "lobby_id")
  private Long lobbyId;

  @Column(name = "user_id")
  private Long userId;

  // Default constructor
  public UserLobbyId() {}

  // Parameterized constructor
  public UserLobbyId(Long lobbyId, Long userId) {
    this.lobbyId = lobbyId;
    this.userId = userId;
  }

  // Getters and setters

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserLobbyId that = (UserLobbyId) o;
    return Objects.equals(lobbyId, that.lobbyId) &&
        Objects.equals(userId, that.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lobbyId, userId);
  }
}
