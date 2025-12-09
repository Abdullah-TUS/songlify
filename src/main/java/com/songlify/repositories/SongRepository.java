package com.songlify.repositories;

import com.songlify.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {

    List<Song> findAllBySingerId(int singerId);

    boolean existsByTitleIgnoreCaseAndSingerId(String title, int singerId);
}
