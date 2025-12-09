package com.songlify.services;

import com.songlify.dto.singer.SingerUpdateDto;
import com.songlify.exceptions.SingerNotFoundException;
import com.songlify.models.Singer;
import com.songlify.repositories.SingerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Singer getSinger(int singerId) {
        return singerRepository.findById(singerId)
                .orElseThrow(() -> new SingerNotFoundException("Singer not found with the ID: " + singerId));
    }


    // I know. give me some time.
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

    public Singer updateSinger(SingerUpdateDto dto) {
        return singerRepository.findById(dto.getId())
                .map(singer -> {
                    if (dto.getName() != null) singer.setName(dto.getName());
                    if (dto.getCountry() != null) singer.setCountry(dto.getCountry());
                    if (dto.getListeners() != null) singer.setListeners(dto.getListeners());

                    return singer;
                }).orElseThrow(() -> new SingerNotFoundException("Singer not found with the ID: " + dto.getId()));
    }

    @Transactional
    public void deleteSinger(int singerId) {
        if (!singerRepository.existsById(singerId)) {
            throw new SingerNotFoundException("Singer not found with the ID: " + singerId);
        }
        singerRepository.deleteById(singerId);
    }
}
