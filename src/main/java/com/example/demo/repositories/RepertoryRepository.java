package com.example.demo.repositories;

import com.example.demo.models.Repertory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepertoryRepository extends CrudRepository<Repertory, Long> {
}
