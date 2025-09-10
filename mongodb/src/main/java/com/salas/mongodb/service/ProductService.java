package com.salas.mongodb.service;

import com.salas.mongodb.entity.Product;
import com.salas.mongodb.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

    public String save(Product product) {
        return productRepository.save(product).getId();
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
