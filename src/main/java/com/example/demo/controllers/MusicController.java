package com.example.demo.controllers;

import com.example.demo.models.Music;
import com.example.demo.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/musics")
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/add")
    public ResponseEntity<Music> addMusic(@RequestParam String title, @RequestParam byte[] pdfFile) {
        return ResponseEntity.ok(musicService.addMusic(title, pdfFile));

    }
    @GetMapping
    public ResponseEntity<List<Music>> getAllMusics() {
        return ResponseEntity.status(HttpStatus.OK).body(musicService.getAllMusics());
    }

}