package com.example.demo.services;

import com.example.demo.models.MusicModel;
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

    public List<MusicModel> getAllMusics() {
        return musicRepository.findAll();
    }

    public Optional<MusicModel> getOneMusic(UUID id) {
        return musicRepository.findById(id);
    }

    public MusicModel saveMusic(MusicModel musicModel) {
        return musicRepository.save(musicModel);
    }

    public void deleteMusic(MusicModel musicModel) {
        musicRepository.delete(musicModel);
    }

    public MusicModel updateMusic(MusicModel musicModel) {
        return musicRepository.save(musicModel);
    }

    public Optional<MusicModel> getOneMusicByTitle(String title) {
        return musicRepository.findByTitle(title);
    }
}