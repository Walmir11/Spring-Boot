package com.example.demo.controllers;

import com.example.demo.models.Band;
import com.example.demo.services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bands")
public class BandController {
    @Autowired
    private BandService bandService;

    @PostMapping
    public Band createBand(@RequestBody Band band) {
        return bandService.save(band);
    }
}
