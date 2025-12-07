package com.songlify.singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerError;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/singers")
public class SingerController {
    private final SingerService singerService;
// new code yes
    @Autowired
    public SingerController(SingerService singerService){
        this.singerService = singerService;
    }

    @GetMapping()
    public List<Singer> getSingers(@RequestParam(required = false)String name,
                                   @RequestParam(required = false)String genre,
                                   @RequestParam(required = false)String country){
        if (name!=null){
            return singerService.getSingersByName(name);
        }
        if(genre!=null){
            return singerService.getSingersInGenre(genre);
        }
        if(country!=null){
            return singerService.getSingersByCountry(country);
        }

        return singerService.getSingers();
    }

    @PostMapping
    public Singer createSinger(@RequestBody Singer singer){
        singerService.addSinger(singer);
        return singer;
    }

    @DeleteMapping(path = "{singerId}")
    public String deleteSinger(@PathVariable int singerId){
        singerService.deleteSinger(singerId);
        return "Singer Delete";
    }
}