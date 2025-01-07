package com.ggoth.snakegamematchmaking.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggoth.snakegamematchmaking.match.Lobby;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "username", nullable = false, unique = true)
  private String username;

  @Column(name = "secret", nullable = false, unique = false, updatable = false)
  private String secret;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "updated_at", nullable = false)
  private Instant updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "queue_joined_at")
  private Instant queueJoinedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lobby_id")
  private Lobby lobby;

  @Column(name = "queue_session_id", unique = true)
  private String queueSessionId;

  @Transient
  private boolean queueJoined;

  public boolean isQueueJoined() {
      return queueJoinedAt != null;
  }
}
