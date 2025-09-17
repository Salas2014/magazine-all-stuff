package com.salas.mongodb.readonly;

import com.salas.mongodb.entity.Product;
import com.salas.mongodb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductReadOnlyService {
    private ProductService productService;

    @Autowired
    public ProductReadOnlyService(ProductService productService) {
        this.productService = productService;
    }

    public CompletableFuture<List<Product>> findAll() {
        return CompletableFuture.completedFuture(productService.findAll());
    }
}
