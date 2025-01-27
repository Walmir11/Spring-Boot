package com.example.demo.controllers;

import com.example.demo.models.BandModel;
import com.example.demo.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/bands")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping
    public ResponseEntity<List<BandModel>> getAllBands() {
        return ResponseEntity.status(HttpStatus.OK).body(bandService.getAllBands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneBand(@PathVariable(value = "id") UUID id) {
        Optional<BandModel> bandO = bandService.getOneBand(id);
        if (bandO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Band not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(bandO.get());
    }

    @PostMapping
    public ResponseEntity<BandModel> saveBand(@RequestBody BandModel bandModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bandService.saveBand(bandModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteBand(@PathVariable(value = "id") UUID id) {
        Optional<BandModel> bandO = bandService.getOneBand(id);
        if (bandO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Band not found.");
        }
        bandService.deleteBand(bandO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Band deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBand(@PathVariable(value = "id") UUID id, @RequestBody BandModel bandModel) {
        Optional<BandModel> bandO = bandService.getOneBand(id);
        if (bandO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Band not found.");
        }
        bandModel.setIdBand(id);
        return ResponseEntity.status(HttpStatus.OK).body(bandService.updateBand(bandModel));
    }
}