package com.ggoth.snakegamematchmaking.lobby;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggoth.snakegamematchmaking.player.Player;
import com.ggoth.snakegamematchmaking.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

  @OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL, orphanRemoval = true)
  List<Player> players;

  @Column(name = "capacity", nullable = false)
  private Integer capacity;
}
