package com.example.springboot.services;

import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductModel> getOneProduct(UUID id) {
        return productRepository.findById(id);
    }

    public ProductModel saveProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }

    public void deleteProduct(ProductModel productModel) {
        productRepository.delete(productModel);
    }

    public ProductModel updateProduct(ProductModel productModel) {
        return productRepository.save(productModel);
    }
}