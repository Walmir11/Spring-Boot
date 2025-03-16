package com.example.demo.repositories;

import com.example.demo.models.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Optional<Music> findByTitle(String title);

}