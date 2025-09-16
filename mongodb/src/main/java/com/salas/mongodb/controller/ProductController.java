package com.salas.mongodb.controller;

import com.salas.mongodb.dto.CategoryDto;
import com.salas.mongodb.dto.ProductDto;
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
    private ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productList = productService.findAll().stream()
                .map(ProductController::convert)
                .toList();
        return ResponseEntity.ok(productList);
    }

    @GetMapping("/{id}")
    private ResponseEntity<ProductDto> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(convert(productService.findById(id)));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProductById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.accepted().build();
    }

   private static ProductDto convert(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .tags(product.getTags())
                .category(CategoryDto.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .description(product.getCategory().getDescription())
                        .build())
                .build();
    }
}
