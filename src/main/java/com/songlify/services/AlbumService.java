package com.songlify.services;


import com.songlify.dto.albums.AlbumGetDto;
import com.songlify.dto.albums.AlbumPatchDto;
import com.songlify.dto.song.SongListDto;
import com.songlify.exceptions.AlbumAlreadyExistsException;
import com.songlify.exceptions.AlbumNotFoundException;
import com.songlify.exceptions.SingerNotFoundException;
import com.songlify.models.Album;
import com.songlify.models.Singer;
import com.songlify.repositories.AlbumRepository;
import com.songlify.repositories.SingerRepository;
import jakarta.transaction.Transactional;
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

    public List<AlbumGetDto> getAlbums() {
        return albumRepository.findAll().stream().map(album -> new AlbumGetDto(
                album.getId(),
                album.getTitle(),
                album.getReleaseDate(),
                album.getSongList().stream().map(song -> new SongListDto(
                        song.getId(), song.getTitle(),
                        song.getDuration()
                )).toList()
        )).toList();
    }

    public List<AlbumGetDto> getSingerAlbums(int singerId) {

        Singer singer = singerRepository.findById(singerId)
                .orElseThrow(() -> new SingerNotFoundException("Singer not found with ID: " + singerId));

        return albumRepository.findBySingerId(singerId).stream()
                .map(album -> new AlbumGetDto(
                        album.getId(),
                        album.getTitle(),
                        album.getReleaseDate(),
                        album.getSongList().stream()
                                .map(song -> new SongListDto(song.getId(), song.getTitle(), song.getDuration()))
                                .toList()
                ))
                .toList();
    }

    public AlbumGetDto createAlbum(Album album) {
        if (album.getSinger().getId() == null)
            throw new IllegalArgumentException("Singer id must be provided");

        if (!singerRepository.existsById(album.getSinger().getId()))
            throw new SingerNotFoundException("Singer not found with ID: " + album.getSinger().getId());

        if (albumRepository.existsByTitleIgnoreCaseAndSingerId(album.getTitle(), album.getSinger().getId()))
            throw new AlbumAlreadyExistsException("Album already exists");

        Album saved = albumRepository.save(album);

        return new AlbumGetDto(
                saved.getId(),
                saved.getTitle(),
                saved.getReleaseDate(),
                null
        );
    }


    @Transactional
    public AlbumGetDto patchAlbum(AlbumPatchDto dto) {
        return albumRepository.findById(dto.getId()).map(album ->
        {
            if (dto.getTitle() != null) album.setTitle(dto.getTitle());
            if (dto.getReleaseDate() != null) album.setReleaseDate(dto.getReleaseDate());
            return new AlbumGetDto(album.getId(), album.getTitle(), album.getReleaseDate(), album.getSongList().stream().map(song -> new SongListDto(song.getId(), song.getTitle(), song.getDuration())).toList());
        }).orElseThrow(() -> new AlbumNotFoundException("Album not found."));
    }

    public void deleteAlbum(int singerId, int albumId) {
        if (!singerRepository.existsById(singerId))
            throw new SingerNotFoundException("Couldn't find a singer with the passed id.");

        if (!albumRepository.existsByIdAndSingerId(albumId, singerId))
            throw new AlbumNotFoundException("Couldn't find an album with the passed id.");

        albumRepository.deleteById(albumId);
    }
}
