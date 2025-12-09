package com.songlify.controllers;

import com.songlify.dto.singer.SingerUpdateDto;
import com.songlify.models.Singer;
import com.songlify.services.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().body(singerService.getSinger(singerId));
    }

    @GetMapping
    public ResponseEntity<List<Singer>> getSingers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String country) {

        return ResponseEntity.ok(singerService.searchSinger(name, genre, country));
    }

    @PostMapping
    public ResponseEntity<Object> createSinger(@RequestBody Singer singer) {
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

    @PatchMapping
    public ResponseEntity<?> patchSinger(@RequestBody SingerUpdateDto dto) {
        return ResponseEntity.ok(singerService.updateSinger(dto));
    }


    public record ErrorResponse(String message) {
    }

}

