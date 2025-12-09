package com.songlify.services;


import com.songlify.dto.albums.AlbumGetDto;
import com.songlify.exceptions.AlbumAlreadyExistsException;
import com.songlify.exceptions.SingerNotFoundException;
import com.songlify.models.Album;
import com.songlify.models.Singer;
import com.songlify.repositories.AlbumRepository;
import com.songlify.repositories.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final SingerRepository singerRepository;


    @Autowired
    public AlbumService(AlbumRepository albumRepository, SingerRepository singerRepository) {
        this.albumRepository = albumRepository;
        this.singerRepository = singerRepository;

    }

    public List<AlbumGetDto> getAlbums(int singerId) {
        Singer singer = singerRepository.findById(singerId).
                orElseThrow(() -> new SingerNotFoundException("Singer not found with the ID: " + singerId));


        return albumRepository.findBySingerId(singerId).stream()
                .map(album -> new AlbumGetDto(album.getId(), album.getTitle(), album.getReleaseDate())).toList();
    }

    public AlbumGetDto createAlbum(Album album) {
        if (album.getSinger().getId() == null)
            throw new IllegalArgumentException("Singer id must be provided");

        if (!singerRepository.existsById(album.getSinger().getId()))
            throw new SingerNotFoundException("Singer not found with the ID:" + album.getSinger().getId());

        if (albumRepository.existsByTitleIgnoreCaseAndSingerId(album.getTitle(), album.getSinger().getId()))
            throw new AlbumAlreadyExistsException("Album already exists.");

        Album dto = albumRepository.save(album);
        return new AlbumGetDto(dto.getId(), album.getTitle(), album.getReleaseDate());
    }
}
