package com.salas.mongodb.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.salas.mongodb.entity.Product;
import com.salas.mongodb.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final Cache<String, Product> productCache;

    @Autowired
    public ProductService(ProductRepository productRepository, Cache<String, Product> productCache) {
        this.productRepository = productRepository;
        this.productCache = productCache;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(String id) {
        return productCache.get(id, key -> productRepository.findById(key).orElseThrow());
    }

    public String save(Product product) {
        String id = productRepository.save(product).getId();
        productCache.put(id, product);
        return id;
    }

    public void deleteById(String id) {
        productRepository.deleteById(id);
        productCache.invalidate(id);
    }
}
