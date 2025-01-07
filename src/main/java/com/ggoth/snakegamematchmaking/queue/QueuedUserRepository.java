package com.ggoth.snakegamematchmaking.queue;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueuedUserRepository extends JpaRepository<QueuedUser, Integer> {
}
