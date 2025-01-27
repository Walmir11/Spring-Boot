package com.example.demo.services;

import com.example.demo.models.BandModel;
import com.example.demo.repositories.BandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BandService {

    @Autowired
    private BandRepository bandRepository;

    public List<BandModel> getAllBands() {
        return bandRepository.findAll();
    }

    public Optional<BandModel> getOneBand(UUID id) {
        return bandRepository.findById(id);
    }

    public BandModel saveBand(BandModel bandModel) {
        return bandRepository.save(bandModel);
    }

    public void deleteBand(BandModel bandModel) {
        bandRepository.delete(bandModel);
    }

    public BandModel updateBand(BandModel bandModel) {
        return bandRepository.save(bandModel);
    }
}