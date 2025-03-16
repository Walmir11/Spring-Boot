package com.example.demo.repositories;

import com.example.demo.models.Band;
import com.example.demo.models.Repertoire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepertoireRepository extends JpaRepository<Repertoire, Long> {

    Optional<Repertoire> findById(Long id);

    List<Repertoire> findByBandOrderByPositionAsc(Band band);

    List<Repertoire> findByBandAndActiveTrueOrderByPositionAsc(Band band);
}