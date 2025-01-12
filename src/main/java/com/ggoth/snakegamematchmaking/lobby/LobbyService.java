package com.ggoth.snakegamematchmaking.lobby;

import com.ggoth.snakegamematchmaking.player.Player;
import com.ggoth.snakegamematchmaking.player.PlayerRepository;
import com.ggoth.snakegamematchmaking.player.UserLobbyId;
import com.ggoth.snakegamematchmaking.user.User;
import com.ggoth.snakegamematchmaking.user.UserRepository;
import com.ggoth.snakegamematchmaking.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LobbyService {
  private final LobbyRepository lobbyRepository;
  private final UserRepository userRepository;
  private final PlayerRepository playerRepository;

  public LobbyService(LobbyRepository lobbyRepository, UserRepository userRepository, PlayerRepository playerRepository) {
    this.lobbyRepository = lobbyRepository;
    this.userRepository = userRepository;
    this.playerRepository = playerRepository;
  }

  public Page<Lobby> getLobbies(Pageable pageable) {
    return lobbyRepository.findAll(pageable);
  }

  @Transactional
  public Player joinlobby(Long lobbyId, LobbyJoinRequest joinRequest) throws Exception {
    Lobby lobby = lobbyRepository.findById(lobbyId).orElse(null);
    User user = userRepository.findById(joinRequest.userId).orElse(null);

    if (lobby == null) {
      throw new Exception("Lobby not found");
    }
    if (user == null) {
      throw new Exception("User not found");
    }

    // TODO move this check into spring security
    if (!user.getSecret().equals(joinRequest.userSecret)) {
      throw new Exception("Secret does not match");
    }

    if(user.isPlaying()){
      throw new Exception("User is already playing");
    }

    if(Objects.equals(lobby.getOccupancy(), lobby.getCapacity())){
      throw new Exception("Lobby is full");
    }

    // All things checked
    // Make Player
    Player player = new Player();
    player.setId(new UserLobbyId(lobby.getId(), user.getId()));
    player.setLobby(lobby);
    player.setUser(user);

    player = playerRepository.save(player);

    return player;
  }
}
