package com.ggoth.snakegamematchmaking.Match;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ggoth.snakegamematchmaking.User.User;
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

  @OneToMany(mappedBy = "lobby", fetch = FetchType.EAGER)
  List<User> users;
}
