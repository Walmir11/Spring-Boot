package com.example.demo.repositories;

import com.example.demo.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SongRepository extends JpaRepository<Song, UUID> {
}
