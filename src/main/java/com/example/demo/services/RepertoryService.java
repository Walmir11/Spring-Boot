package com.example.demo.services;

import com.example.demo.models.Band;
import com.example.demo.models.Repertory;
import com.example.demo.repositories.RepertoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepertoryService {

    @Autowired
    private RepertoryRepository repertoryRepository;

    public List<Repertory> findByBand(Band band) {
        return repertoryRepository.findByBanda(band);
    }

    public Repertory save(Repertory repertory) {
        return repertoryRepository.save(repertory);
    }

    public Optional<Repertory> findById(Long id) {
        return repertoryRepository.findById(id);
    }

    public Iterable<Repertory> findAll() {
        return repertoryRepository.findAll();
    }

    public void deleteById(Long id) {
        repertoryRepository.deleteById(id);
    }
}
