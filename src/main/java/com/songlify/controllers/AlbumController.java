package com.songlify.controllers;

import com.songlify.dto.albums.AlbumGetDto;
import com.songlify.models.Album;
import com.songlify.services.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.songlify.constants.URLs.ALBUM.*;

@RestController
@RequestMapping(BASE_URL)
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<List<AlbumGetDto>> getAlbums() {
        return ResponseEntity.ok(albumService.getAlbums());
    }

    @GetMapping(GET_BY_SINGER)
    public ResponseEntity<List<AlbumGetDto>> getSingerAlbums(@PathVariable int singerId) {
        return ResponseEntity.ok(albumService.getSingerAlbums(singerId));
    }

    @PostMapping
    public ResponseEntity<AlbumGetDto> createAlbum(@RequestBody Album album) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.createAlbum(album));
    }
}

