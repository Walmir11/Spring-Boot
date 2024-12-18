package com.example.demo.services;

import com.example.demo.models.Band;
import com.example.demo.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BandService {
    @Autowired
    private BandRepository bandRepository;

    public Band save(Band band) {
        return bandRepository.save(band);
    }
}
