package com.example.demo.repositories;

import com.example.demo.models.Band;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BandRepository extends JpaRepository<Band, Long> {
}
