package com.ggoth.snakegamematchmaking.lobby;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LobbyService {
  private final LobbyRepository lobbyRepository;

  public LobbyService(LobbyRepository lobbyRepository) {
    this.lobbyRepository = lobbyRepository;
  }

  public Page<Lobby> getLobbies(Pageable pageable) {
    return lobbyRepository.findAll(pageable);
  }
}
