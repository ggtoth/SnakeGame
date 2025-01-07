package com.ggoth.snakegamematchmaking.queue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ggoth.snakegamematchmaking.lobby.Lobby;
import com.ggoth.snakegamematchmaking.user.User;
import com.ggoth.snakegamematchmaking.user.UserLobbyId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "queued_users")
public class QueuedUser {
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

  @Column(name = "ws_session_id")
  private String wsSessionId;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(name = "joined_at")
  private Instant joinedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  @Column(name = "updated_at")
  private Instant updatedAt;
}
