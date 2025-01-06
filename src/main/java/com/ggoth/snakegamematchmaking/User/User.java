package com.ggoth.snakegamematchmaking.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggoth.snakegamematchmaking.Match.Lobby;
import com.ggoth.snakegamematchmaking.names.Name;
import com.ggoth.snakegamematchmaking.names.NamesRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

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

  @Transient
  private boolean queueJoined;

  public boolean isQueueJoined() {
      return queueJoinedAt != null;
  }
}
