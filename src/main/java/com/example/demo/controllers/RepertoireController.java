package com.example.demo.controllers;

import com.example.demo.models.RepertoireModel;
import com.example.demo.services.RepertoireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/repertoires")
public class RepertoireController {

    @Autowired
    private RepertoireService repertoireService;

    @GetMapping
    public ResponseEntity<List<RepertoireModel>> getAllRepertoires() {
        return ResponseEntity.status(HttpStatus.OK).body(repertoireService.getAllRepertoires());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRepertoire(@PathVariable(value = "id") UUID id) {
        Optional<RepertoireModel> repertoireO = repertoireService.getOneRepertoire(id);
        if (repertoireO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Repertoire not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(repertoireO.get());
    }

    @PostMapping
    public ResponseEntity<RepertoireModel> saveRepertoire(@RequestBody RepertoireModel repertoireModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repertoireService.saveRepertoire(repertoireModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRepertoire(@PathVariable(value = "id") UUID id) {
        Optional<RepertoireModel> repertoireO = repertoireService.getOneRepertoire(id);
        if (repertoireO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Repertoire not found.");
        }
        repertoireService.deleteRepertoire(repertoireO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Repertoire deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateRepertoire(@PathVariable(value = "id") UUID id, @RequestBody RepertoireModel repertoireModel) {
        Optional<RepertoireModel> repertoireO = repertoireService.getOneRepertoire(id);
        if (repertoireO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Repertoire not found.");
        }
        repertoireModel.setIdRepertoire(id);
        return ResponseEntity.status(HttpStatus.OK).body(repertoireService.updateRepertoire(repertoireModel));
    }
}