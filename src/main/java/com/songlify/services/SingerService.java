package com.songlify.services;

import com.songlify.dto.singer.SingerUpdateDto;
import com.songlify.models.Singer;
import com.songlify.repositories.SingerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SingerService {

    private final SingerRepository singerRepository;

    @Autowired
    public SingerService(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    public List<Singer> getSingers() {
        return singerRepository.findAll();
    }

    public Optional<Singer> getSinger(int singerId) {
        return singerRepository.findById(singerId);
    }

    public List<Singer> searchSinger(String name, String genre, String country) {
        if (name != null) return singerRepository.findByNameContainingIgnoreCase(name);
        if (genre != null) return singerRepository.findByGenreIgnoreCase(genre);
        if (country != null) return singerRepository.findByCountryContainingIgnoreCase(country);
        return singerRepository.findAll();
    }

    public Singer addSinger(Singer singer) {
        if (singerRepository.existsByName(singer.getName())) {
            return null;
        }
        return singerRepository.save(singer);
    }

    public Singer updateSinger(SingerUpdateDto singer) {
        singerRepository.findById();
    }

    @Transactional
    public void deleteSinger(int singerId) {
        singerRepository.deleteById(singerId);
    }
}
