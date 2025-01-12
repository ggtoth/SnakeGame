package com.ggoth.snakegamematchmaking.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggoth.snakegamematchmaking.lobby.Lobby;
import com.ggoth.snakegamematchmaking.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {
  @EmbeddedId
  @Column(unique = true, nullable = false)
  private UserLobbyId id;

  @JsonIgnore
  @ManyToOne
  @MapsId("lobbyId")
  @JoinColumn(name = "lobby_id")
  private Lobby lobby;

  @JsonIgnore
  @OneToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id")
  private User user;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "joined_at")
  private Instant joinedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Instant updatedAt;
}
