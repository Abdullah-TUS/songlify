package com.songlify.services;

import com.songlify.dto.song.SongGetDto;
import com.songlify.exceptions.SingerAlreadyExistsException;
import com.songlify.exceptions.SingerNotFoundException;
import com.songlify.models.Song;
import com.songlify.repositories.SingerRepository;
import com.songlify.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final SingerRepository singerRepository;

    @Autowired
    public SongService(SongRepository songRepository, SingerRepository singerRepository) {
        this.songRepository = songRepository;
        this.singerRepository = singerRepository;
    }

    public SongGetDto addSong(Song song) {
        if (song.getSinger().getId() == null) {
            throw new IllegalArgumentException("missing singer ID");
        }

        if (!singerRepository.existsById(song.getSinger().getId())) {
            throw new SingerNotFoundException("No singer found with the id.");
        }

        if (songRepository.existsByTitleIgnoreCaseAndSingerId(song.getTitle(), song.getSinger().getId())) {
            throw new SingerAlreadyExistsException("Singer not found mate");
        }
        Song tempSong = songRepository.save(song);
        return new SongGetDto(tempSong.getId(), tempSong.getTitle(), tempSong.getDuration());
    }

    public List<SongGetDto> getSongs(int singerId) {
        return songRepository.findAllBySingerId(singerId)
                .stream().map(song -> new SongGetDto(song.getId(), song.getTitle(), song.getDuration())).toList();
    }

}
