package com.ggoth.snakegamematchmaking.User;

import jakarta.validation.Valid;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findUserByUsername(String username);

  @Query("SELECT u FROM User u WHERE u.queueJoinedAt IS NOT NULL ORDER BY u.queueJoinedAt ASC")
  Page<User> findAllUsersQueueJoinedAtNotNull(Pageable pageable);

  User findUsersById(@Valid Long id);
}
