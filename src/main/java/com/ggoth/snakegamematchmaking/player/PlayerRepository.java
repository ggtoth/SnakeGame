package com.ggoth.snakegamematchmaking.player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, UserLobbyId> {

}
