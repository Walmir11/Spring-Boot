package com.example.demo.services;

import com.example.demo.models.Band;
import com.example.demo.models.Music;
import com.example.demo.models.Repertoire;
import com.example.demo.repositories.BandRepository;
import com.example.demo.repositories.MusicRepository;
import com.example.demo.repositories.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepertoireService {

    @Autowired
    private RepertoireRepository repertoireRepository;

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private MusicRepository musicRepository;

    public Repertoire addMusic(Long bandId,Long musicId,Integer position) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found"));
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new RuntimeException("Music not found"));

        Repertoire repertoire = Repertoire.builder()
                .band(band)
                .music(music)
                .position(position)
                .active(true)
                .build();
        return repertoireRepository.save(repertoire);
    }

    public List<Repertoire> listActiveRepertoire(Long bandId) {
        Band band = bandRepository.findById(bandId)
                .orElseThrow(() -> new RuntimeException("Band not found"));
        return repertoireRepository.findByBandAndActiveTrueOrderByPositionAsc(band);
    }

    public void removeMusic(Long repertoireId) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId)
                .orElseThrow(() -> new RuntimeException("Repertoire not found"));
        repertoire.setActive(false);
        repertoireRepository.save(repertoire);
    }

    public Repertoire updatePosition(Long repertoireId, Integer position) {
        Repertoire repertoire = repertoireRepository.findById(repertoireId)
                .orElseThrow(() -> new RuntimeException("Repertoire not found"));
        repertoire.setPosition(position);
        return repertoireRepository.save(repertoire);
    }
}