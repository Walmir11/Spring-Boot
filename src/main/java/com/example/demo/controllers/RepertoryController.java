package com.example.demo.controllers;

import com.example.demo.models.Repertory;
import com.example.demo.models.Song;
import com.example.demo.services.RepertoryService;
import com.example.demo.services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/repertories")
public class RepertoryController {

    @Autowired
    private RepertoryService repertoryService;

    @Autowired
    private SongService songService;

    @PostMapping
    public ResponseEntity<Repertory> createRepertory(@RequestBody Repertory repertory) {
        Repertory savedRepertory = repertoryService.save(repertory);
        return ResponseEntity.ok(savedRepertory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repertory> getRepertoryById(@PathVariable Long id) {
        Optional<Repertory> repertory = repertoryService.findById(id);
        return repertory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<Repertory>> getAllRepertories() {
        Iterable<Repertory> repertories = repertoryService.findAll();
        return ResponseEntity.ok(repertories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repertory> updateRepertory(@PathVariable Long id, @RequestBody Repertory repertory) {
        Optional<Repertory> existingRepertory = repertoryService.findById(id);
        if (existingRepertory.isPresent()) {
            repertory.setId(id);
            Repertory updatedRepertory = repertoryService.save(repertory);
            return ResponseEntity.ok(updatedRepertory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepertory(@PathVariable Long id) {
        if (repertoryService.findById(id).isPresent()) {
            repertoryService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{repertoryId}/songs/{songId}")
    public ResponseEntity<Repertory> addSongToRepertory(@PathVariable Long repertoryId, @PathVariable UUID songId) {
        Optional<Repertory> repertory = repertoryService.findById(repertoryId);
        Optional<Song> song = songService.findById(songId);
        if (repertory.isPresent() && song.isPresent()) {
            repertory.get().getMusicas().add(song.get());
            repertoryService.save(repertory.get());
            return ResponseEntity.ok(repertory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{repertoryId}/songs/{songId}")
    public ResponseEntity<Repertory> removeSongFromRepertory(@PathVariable Long repertoryId, @PathVariable UUID songId) {
        Optional<Repertory> repertory = repertoryService.findById(repertoryId);
        Optional<Song> song = songService.findById(songId);
        if (repertory.isPresent() && song.isPresent()) {
            repertory.get().getMusicas().remove(song.get());
            repertoryService.save(repertory.get());
            return ResponseEntity.ok(repertory.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
