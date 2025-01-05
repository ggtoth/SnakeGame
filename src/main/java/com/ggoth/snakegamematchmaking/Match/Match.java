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
@Table(name = "match")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Match {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
  List<User> users;
}
