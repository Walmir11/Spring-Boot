package com.example.demo.repositories;

import com.example.demo.models.Band;
import com.example.demo.models.LiveExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiveExecutionRepository extends JpaRepository<LiveExecution, Long> {


    //    Optional<LiveExecution> findByBandAndExecutionIsTrue(Long band, Boolean execution);
    Optional<LiveExecution> findByBandAndExecutionIsTrue(Band band);
}