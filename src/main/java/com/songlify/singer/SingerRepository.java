package com.songlify.singer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Long> {
    Optional<Singer> findByName(String name); // Spring Data auto-implements this
}
