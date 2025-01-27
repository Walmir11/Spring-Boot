package com.example.demo.controllers;

import com.example.demo.models.MusicModel;
import com.example.demo.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping
    public ResponseEntity<List<MusicModel>> getAllMusics() {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.getAllMusics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneMusic(@PathVariable(value = "id") UUID id) {
        Optional<MusicModel> musicO = musicService.getOneMusic(id);
        if (musicO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Music not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(musicO.get());
    }

    @PostMapping
    public ResponseEntity<MusicModel> saveMusic(@RequestBody MusicModel musicModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(musicService.saveMusic(musicModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMusic(@PathVariable(value = "id") UUID id) {
        Optional<MusicModel> musicO = musicService.getOneMusic(id);
        if (musicO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Music not found.");
        }
        musicService.deleteMusic(musicO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Music deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMusic(@PathVariable(value = "id") UUID id, @RequestBody MusicModel musicModel) {
        Optional<MusicModel> musicO = musicService.getOneMusic(id);
        if (musicO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Music not found.");
        }
        musicModel.setIdMusic(id);
        return ResponseEntity.status(HttpStatus.OK).body(musicService.updateMusic(musicModel));
    }
}