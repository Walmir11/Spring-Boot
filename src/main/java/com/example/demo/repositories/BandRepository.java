package com.example.demo.repositories;

import com.example.demo.models.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    Optional<Band> findById(Long id);
}