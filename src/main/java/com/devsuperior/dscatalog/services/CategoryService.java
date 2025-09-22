package com.devsuperior.dscatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }   
    
}
