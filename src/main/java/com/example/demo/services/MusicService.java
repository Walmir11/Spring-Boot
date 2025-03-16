package com.example.demo.services;

import com.example.demo.models.Music;
import com.example.demo.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;


    public Music addMusic(String title, byte[] arquivoPdf) {
        Music music = Music.builder()
                .title(title)
                .pdfFile(arquivoPdf)
                .build();
        return musicRepository.save(music);
    }

    public List<Music> getAllMusics() {
        return musicRepository.findAll();
    }

    public Music getMusicById(Long musicId) {
        return musicRepository.findById(musicId).orElseThrow(() -> new RuntimeException("Music not found"));
    }

    public void deleteMusic(Music musicModel) {
        musicRepository.delete(musicModel);
    }

    public Music updateMusic(Music musicModel) {
        return musicRepository.save(musicModel);
    }

    public Optional<Music> getOneMusicByTitle(String title) {
        return musicRepository.findByTitle(title);
    }
}