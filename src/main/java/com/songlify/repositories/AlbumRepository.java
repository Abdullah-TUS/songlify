package com.songlify.repositories;

import com.songlify.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    // checks if the singer has album with the passed title
    boolean existsByTitleIgnoreCaseAndSingerId(String albumTitle, Integer singerId);

    List<Album> findBySingerId(Integer singerId);
}
