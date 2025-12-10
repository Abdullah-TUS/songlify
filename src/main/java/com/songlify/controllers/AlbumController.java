package com.songlify.controllers;

import com.songlify.dto.albums.AlbumGetDto;
import com.songlify.models.Album;
import com.songlify.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.songlify.constants.URLs.ALBUM.*;

@RestController
@RequestMapping(path = BASE_URL)
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(path = "/{singerId}")
    public ResponseEntity<List<AlbumGetDto>> getAlbums(@PathVariable int singerId) {
        return ResponseEntity.ok().body(albumService.getAlbums(singerId));
    }

    @PostMapping
    public ResponseEntity<AlbumGetDto> createAlbum(@RequestBody Album album) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.createAlbum(album));
    }
}
