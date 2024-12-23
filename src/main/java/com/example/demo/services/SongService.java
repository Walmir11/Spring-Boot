package com.example.demo.services;

import com.example.demo.models.Band;
import com.example.demo.models.Song;
import com.example.demo.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public Optional<Song> findById(UUID id) {
        return songRepository.findById(id);
    }

    public Iterable<Song> findAll() {
        return songRepository.findAll();
    }

    public void deleteById(UUID id) {
        songRepository.deleteById(id);
    }

    public List<Song> findByTitleContaining(String title) {
        return songRepository.findByTitleContaining(title);
    }

    public Optional<Song> findByTitleAndBand(String title, Band band) {
        return songRepository.findByTitleAndBand(title, band);
    }

    public Song updateSong(UUID id, Song updatedSong) {
        Optional<Song> existingSong = songRepository.findById(id);
        if (existingSong.isPresent()) {
            Song song = existingSong.get();
            song.setTitle(updatedSong.getTitle());
            song.setPdfPath(updatedSong.getPdfPath());
            song.setActive(updatedSong.isActive());
            song.setBand(updatedSong.getBand());
            return songRepository.save(song);
        } else {
            return null; // Ou lançar uma exceção indicando que a música não foi encontrada
        }
    }
}
