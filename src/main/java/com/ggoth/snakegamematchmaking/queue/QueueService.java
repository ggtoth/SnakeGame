package com.ggoth.snakegamematchmaking.queue;

import com.ggoth.snakegamematchmaking.match.Lobby;
import com.ggoth.snakegamematchmaking.match.LobbyRepository;
import com.ggoth.snakegamematchmaking.user.User;
import com.ggoth.snakegamematchmaking.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
public class QueueService {
  private final UserRepository userRepository;
  private final LobbyRepository lobbyRepository;

  public QueueService(UserRepository userRepository, LobbyRepository lobbyRepository) {
    this.userRepository = userRepository;
    this.lobbyRepository = lobbyRepository;
  }

  @Transactional
  public User queueUp(User user){
    User foundUser = userRepository.findUsersById(user.getId());

    if (foundUser != null && Objects.equals(user.getSecret(), foundUser.getSecret())) {
      foundUser.setQueueJoinedAt(Instant.now());
      foundUser.setQueueSessionId(user.getQueueSessionId());
      foundUser = userRepository.save(foundUser);
      return userRepository.findUsersById(foundUser.getId());
    }
    return null;
  }

  @Transactional
  public User queueDown(User user){
    User foundUser = userRepository.findUsersById(user.getId());

    if (foundUser != null && Objects.equals(user.getSecret(), foundUser.getSecret())) {
      foundUser.setQueueJoinedAt(null);
      foundUser.setQueueSessionId(null);
      foundUser = userRepository.save(foundUser);
      return userRepository.findUsersById(foundUser.getId());
    }
    return null;
  }

  public void queueDisconnect(String sessionId){
    User user = userRepository.findUserByQueueSessionId(sessionId);
    queueDown(user);
  }
}
