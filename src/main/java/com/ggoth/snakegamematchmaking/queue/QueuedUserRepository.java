package com.ggoth.snakegamematchmaking.queue;

import com.ggoth.snakegamematchmaking.user.UserLobbyId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QueuedUserRepository extends JpaRepository<QueuedUser, Integer> {
  void removeById(UserLobbyId id);

  void removeByWsSessionId(String sessionId);

  Optional<QueuedUser> findByWsSessionId(String wsSessionId);
}
