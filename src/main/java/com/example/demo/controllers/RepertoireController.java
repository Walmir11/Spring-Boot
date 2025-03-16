package com.example.demo.controllers;

import com.example.demo.models.Repertoire;
import com.example.demo.services.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/repertoires")
public class RepertoireController {

    @Autowired
    private RepertoireService repertoireService;

    @PostMapping("/add")
    public ResponseEntity<Repertoire> addRepertoire(
            @RequestParam Long bandId,
            @RequestParam Long musicId,
            @RequestParam Integer position) {
        return ResponseEntity.ok(repertoireService.addMusic(bandId, musicId, position));
    }

    @GetMapping("/list/{bandId}")
    public ResponseEntity<List<Repertoire>> getRepertoireByBandId(@PathVariable(value = "bandId") Long bandId) {
        return ResponseEntity.status(HttpStatus.OK).body(repertoireService.listActiveRepertoire(bandId));
    }

    @PostMapping("/remove/{repertoireId}")
    public ResponseEntity<Object> removeRepertoire(@PathVariable Long repertoireId) {
        repertoireService.removeMusic(repertoireId);
        return ResponseEntity.ok("Music removed from repertoire.");
    }

    @PostMapping("/update/{repertoireId}")
    public ResponseEntity<String> updateRepertoire(
            @RequestParam Long repertoireId,
            @RequestParam Integer position) {
        repertoireService.updatePosition(repertoireId, position);
        return ResponseEntity.ok("Position updated.");
    }
}