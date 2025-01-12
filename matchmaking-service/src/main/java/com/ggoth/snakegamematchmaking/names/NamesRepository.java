package com.ggoth.snakegamematchmaking.names;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NamesRepository extends JpaRepository<Name, Long> {
  @Query(value = "SELECT * FROM names ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
  Name findRandomName();
}
