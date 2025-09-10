package com.salas.mongodb.controller;

import com.salas.mongodb.entity.Product;
import com.salas.mongodb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    private ResponseEntity<String> addProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping
    private ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    private ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
