package com.example.demo.controllers;

import com.example.demo.models.Band;
import com.example.demo.models.User;
import com.example.demo.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @PostMapping("/create")
    public ResponseEntity<Band> createBand(@RequestParam String name, @RequestParam Long leaderID) {

        Band band = bandService.createBand(name, leaderID);

        if (band == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(band);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Band>> getBandsByUserId(@PathVariable Long userId) {
        List<Band> band = bandService.listUserBands(userId);
        return ResponseEntity.ok(band);
    }

    @PostMapping("/addMembers")
    public ResponseEntity<Band> addMembersToBand(@RequestParam Long bandId, @RequestBody List<Long> memberIds) {
        Band band = bandService.addMembersToBand(bandId, memberIds);
        return ResponseEntity.ok(band);
    }
    @PostMapping("/addMember")
    public ResponseEntity<Band> addMemberToBand(@RequestParam Long bandId, @RequestParam Long memberId) {
        Band band = bandService.addMemberToBand(bandId, memberId);
        return ResponseEntity.ok(band);
    }

}