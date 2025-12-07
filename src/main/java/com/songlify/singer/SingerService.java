package com.songlify.singer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SingerService {
    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository){
        this.singerRepository=singerRepository;
    }
    public List<Singer> getSingers() {
        return singerRepository.findAll();
    }

    public List<Singer> getSingersInGenre(String genre){
        return singerRepository.findAll().stream()
                .filter(singer -> genre.equalsIgnoreCase(singer.getGenre()))
                .collect(Collectors.toList());
    }

    public List<Singer> getSingersByName(String searchName){
        return singerRepository.findAll().stream()
                .filter(singer -> singer.getName().toLowerCase().contains(searchName.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Singer> getSingersByCountry(String searchCountry){
        return singerRepository.findAll().stream()
                .filter(singer -> singer.getCountry().toLowerCase().contains(searchCountry.toLowerCase()))
                .collect(Collectors.toList());
    }
    public void addSinger(Singer singer){
        singerRepository.save(singer);
    }

    @Transactional
    public void deleteSinger(long singerId){
        singerRepository.deleteById(singerId);
    }
}
