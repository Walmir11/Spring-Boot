package com.example.springboot;

import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Create a new product
        ProductModel product = new ProductModel();
        product.setIdProduct(UUID.randomUUID());
        product.setName("Sample Product");
        product.setValue(new BigDecimal("19.99"));

        ProductModel product1 = new ProductModel();
        product1.setIdProduct(UUID.randomUUID());
        product1.setName("Product 1");
        product1.setValue(new BigDecimal("9.99"));

        ProductModel product2 = new ProductModel();
        product2.setIdProduct(UUID.randomUUID());
        product2.setName("Product 2");
        product2.setValue(new BigDecimal("19.99"));

        // Save the product to the database
        productRepository.save(product);
        productRepository.save(product1);
        productRepository.save(product2);

        // Update the product
        updateProduct(product.getIdProduct(), "Updated Product", new BigDecimal("29.99"));

        // Get all products
        getAllProducts();

        // Print data from a specific table
        printDataFromSpecificTable("TB_PRODUCTS");

        // Delete the product
        deleteProduct(product1.getIdProduct());
    }

    private void updateProduct(UUID id, String name, BigDecimal value) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isPresent()) {
            ProductModel product = productO.get();
            product.setName(name);
            product.setValue(value);
            productRepository.save(product);
            System.out.println("Product updated: " + product);
        } else {
            System.out.println("Product not found for update.");
        }
    }

    private void getAllProducts() {
        List<ProductModel> products = productRepository.findAll();
        System.out.println("All products:");
        for (ProductModel product : products) {
            System.out.println(product);
        }
    }

    private void printDataFromSpecificTable(String tableName) {
        String query = "SELECT * FROM " + tableName;
        List<ProductModel> products = jdbcTemplate.query(query, new BeanPropertyRowMapper<>(ProductModel.class));

        if (!products.isEmpty()) {
            System.out.println("Data from table " + tableName + ":");
            for (ProductModel product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("No data found in the table " + tableName + ".");
        }
    }

    private void deleteProduct(UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isPresent()) {
            productRepository.delete(productO.get());
            System.out.println("Product deleted: " + productO.get());
        } else {
            System.out.println("Product not found for deletion.");
        }
    }
}