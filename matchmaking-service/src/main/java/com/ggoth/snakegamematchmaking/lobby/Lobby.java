package com.ggoth.snakegamematchmaking.lobby;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggoth.snakegamematchmaking.player.Player;
import com.ggoth.snakegamematchmaking.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lobbies")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Lobby {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @JsonIgnore
  @OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Player> players;

  @Column(name = "capacity", nullable = false)
  private Integer capacity;

  // TODO place validator here
  @Column(name = "websocket_url", unique = true, nullable = false, updatable = false)
  private String websocketUrl;

  @Transient
  private Integer occupancy;

  @PostLoad
  private void postLoad() {
    this.occupancy = players != null ? players.size() : 0;
  }
}
