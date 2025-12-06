package com.songlify.singer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/singers")
public class SingerController {
    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService){
        this.singerService = singerService;
    }

    @GetMapping
    public List<Singer> getSingers(){
        return singerService.getSingers();
    }
}

