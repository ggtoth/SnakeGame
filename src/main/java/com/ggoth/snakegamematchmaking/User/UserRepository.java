package com.ggoth.snakegamematchmaking.User;

import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User getUserById(Long id);

  User findUserByUsername(String username);


}
