package com.salas.mongodb.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.salas.mongodb.entity.Product;
import com.salas.mongodb.repo.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    private Cache<String, Product> cache;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(Duration.ofMinutes(10))
                .build();
        productService = new ProductService(productRepository, cache);
    }

    @Test
    void testFindById_CacheMiss_ShouldLoadFromRepository() {
        String id = "1";

        Product product = Product.builder().id(id).name("name").build();
        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        Product result = productService.findById(id);

        assertEquals(product, result);
        assertEquals(product, cache.getIfPresent(id));
    }

    @Test
    void testFindById_CacheHit_ShouldNotCallRepository() {
        String id = "1";
        Product product = Product.builder().id(id).name("name").build();
        cache.put(id, product);

        Product result = productService.findById(id);

        assertEquals(product, result);
        verify(productRepository, Mockito.never()).findById(id);
    }
}