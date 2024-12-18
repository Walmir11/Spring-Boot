package com.example.demo.services;

import com.example.demo.models.Music;
import com.example.demo.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    private MusicRepository musicRepository;

    public Music save(Music music) {
        return musicRepository.save(music);
    }

    public List<Music> findAll() {
        return musicRepository.findAll();
    }
}