package com.example.demo.services;

import com.example.demo.models.Band;
import com.example.demo.models.LiveExecution;
import com.example.demo.models.Repertoire;
import com.example.demo.repositories.BandRepository;
import com.example.demo.repositories.LiveExecutionRepository;
import com.example.demo.repositories.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LiveExecutionService {
    @Autowired
    private LiveExecutionRepository executionRepository;

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Autowired
    private BandRepository bandRepository;

    public LiveExecution startExecution(Long bandId, Long repertoireId) {

        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found"));
        Repertoire actualMusic = repertoireRepository.findById(repertoireId)
                .orElseThrow(() -> new RuntimeException("Repertoire not found"));

        executionRepository.findByBandAndExecutionIsTrue(band)
                .ifPresent(execution -> {
                    execution.setExecution(false);
                    executionRepository.save(execution);
                });
        LiveExecution execution = LiveExecution.builder()
                .band(band)
                .repertoire(actualMusic)
                .execution(true)
                .build();
        return executionRepository.save(execution);
    }

    public Optional<LiveExecution> findActualExecution(Long bandId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found"));
        return executionRepository.findByBandAndExecutionIsTrue(band);
    }
}
