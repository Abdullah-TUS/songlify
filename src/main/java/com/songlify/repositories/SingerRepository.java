package com.songlify.repositories;

import com.songlify.models.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepository extends JpaRepository<Singer, Integer> {

    List<Singer> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

    List<Singer> findByGenreIgnoreCase(String genre);

    List<Singer> findByCountryContainingIgnoreCase(String country);
}

