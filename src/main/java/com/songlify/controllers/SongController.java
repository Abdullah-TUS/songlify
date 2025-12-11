package com.songlify.controllers;


import com.songlify.dto.song.SongGetDto;
import com.songlify.models.Song;
import com.songlify.services.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.songlify.constants.URLs.SONG.*;

@RestController
@RequestMapping(BASE_URL)
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }


    @GetMapping(GET_BY_SINGER)
    public ResponseEntity<List<SongGetDto>> getSongs(@PathVariable int singerId) {
        return ResponseEntity.ok(songService.getSongs(singerId));
    }

    @PostMapping
    public ResponseEntity<SongGetDto> createSong(@RequestBody Song song) {
        SongGetDto createdSong = songService.addSong(song);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSong);
    }
}
