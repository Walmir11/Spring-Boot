package com.example.demo.services;

import com.example.demo.models.Song;
import com.example.demo.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    @Autowired
    private SongRepository songRepository;

    public Song save(Song song) {
        return songRepository.save(song);
    }
}
