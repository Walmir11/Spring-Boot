package com.example.demo.repositories;

import com.example.demo.models.Band;
import com.example.demo.models.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SongRepository extends CrudRepository<Song, UUID> {
    List<Song> findByTitleContaining(String title);
    Optional<Song> findByTitleAndBand(String title, Band band);
}
