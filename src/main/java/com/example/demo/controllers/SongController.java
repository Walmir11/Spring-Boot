package com.example.demo.controllers;

import com.example.demo.models.Song;
import com.example.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.save(song);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return songService.findAll();
    }
}
