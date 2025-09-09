package com.salas.mongodb.repo;

import com.salas.mongodb.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
