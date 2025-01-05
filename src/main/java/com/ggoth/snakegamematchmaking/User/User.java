package com.ggoth.snakegamematchmaking.User;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggoth.snakegamematchmaking.Match.Match;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
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
  @JoinColumn(name = "match_id")
  private Match match;

  @Transient
  private boolean queueJoined;

  public boolean isQueueJoined() {
      return queueJoinedAt != null;
  }
}
