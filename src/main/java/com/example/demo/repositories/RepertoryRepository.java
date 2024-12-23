// RepertoryRepository.java
package com.example.demo.repositories;

import com.example.demo.models.Band;
import com.example.demo.models.Repertory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepertoryRepository extends CrudRepository<Repertory, Long> {
    List<Repertory> findByBanda(Band banda);
}
