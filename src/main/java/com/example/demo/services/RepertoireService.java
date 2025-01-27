package com.example.demo.services;

import com.example.demo.models.RepertoireModel;
import com.example.demo.repositories.RepertoireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RepertoireService {

    @Autowired
    private RepertoireRepository repertoireRepository;

    public List<RepertoireModel> getAllRepertoires() {
        return repertoireRepository.findAll();
    }

    public Optional<RepertoireModel> getOneRepertoire(UUID id) {
        return repertoireRepository.findById(id);
    }

    public RepertoireModel saveRepertoire(RepertoireModel repertoireModel) {
        if (repertoireModel.getBand() != null) {
            repertoireModel.setBandName(repertoireModel.getBand().getName());
        }
        return repertoireRepository.save(repertoireModel);
    }

    public void deleteRepertoire(RepertoireModel repertoireModel) {
        repertoireRepository.delete(repertoireModel);
    }

    public RepertoireModel updateRepertoire(RepertoireModel repertoireModel) {
        return repertoireRepository.save(repertoireModel);
    }
}