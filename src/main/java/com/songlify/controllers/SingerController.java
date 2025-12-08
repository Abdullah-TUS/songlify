package com.songlify.controllers;

import com.songlify.models.Singer;
import com.songlify.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/singers")
public class SingerController {

    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping(path = "/{singerId}")
    public ResponseEntity<Singer> getSinger(@PathVariable int singerId) {
        Optional<Singer> singer = singerService.getSinger(singerId);
        return singer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    public ResponseEntity<List<Singer>> getSingers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String country) {

        return ResponseEntity.ok(singerService.searchSinger(name, genre, country));
    }

    @PostMapping
    public ResponseEntity<?> createSinger(@RequestBody Singer singer) {
        Singer created = singerService.addSinger(singer);

        if (created == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("Singer already exists."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{singerId}")
    public ResponseEntity<Void> deleteSinger(@PathVariable int singerId) {
        singerService.deleteSinger(singerId);
        return ResponseEntity.noContent().build();
    }

    public record ErrorResponse(String message) {
    }

}

