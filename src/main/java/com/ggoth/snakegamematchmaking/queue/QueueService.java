package com.ggoth.snakegamematchmaking.queue;

import com.ggoth.snakegamematchmaking.lobby.Lobby;
import com.ggoth.snakegamematchmaking.lobby.LobbyRepository;
import com.ggoth.snakegamematchmaking.user.User;
import com.ggoth.snakegamematchmaking.user.UserLobbyId;
import com.ggoth.snakegamematchmaking.user.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service
public class QueueService {
  private final UserRepository userRepository;
  private final LobbyRepository lobbyRepository;
  private final QueuedUserRepository queuedUserRepository;

  public QueueService(UserRepository userRepository, LobbyRepository lobbyRepository, QueuedUserRepository queuedUserRepository) {
    this.userRepository = userRepository;
    this.lobbyRepository = lobbyRepository;
    this.queuedUserRepository = queuedUserRepository;
  }

  @Transactional
  public User queueUp(Long userId, Long lobbyId, String secret, String sessionId) {
    Optional<User> optionalUser = userRepository.findById(userId);
    Optional<Lobby> optionalLobby = lobbyRepository.findById(lobbyId);

    if(optionalUser.isEmpty() || optionalLobby.isEmpty())
      return null;

    User user = optionalUser.get();
    Lobby lobby = optionalLobby.get();

    // TODO do this with spring security!!!
    if (!user.getSecret().equals(secret))
      return null;

    if(user.isQueueJoined())
      return null;

    QueuedUser queuedUser = new QueuedUser();

    UserLobbyId userLobbyId = new UserLobbyId(userId, lobbyId);
    queuedUser.setId(userLobbyId);
    queuedUser.setUser(user);
    queuedUser.setLobby(lobby);
    queuedUser.setWsSessionId(sessionId);

    queuedUser = queuedUserRepository.save(queuedUser);

    user.setQueuedUser(queuedUser);
    user = userRepository.save(user);

    return userRepository.findById(user.getId()).orElse(null);
  }

  @Transactional
  public User queueDown(User user){
    return null;
  }

  public void queueDisconnect(String sessionId){
  }
}
