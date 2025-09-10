package com.salas.mongodb.repo;

import com.salas.mongodb.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, String> {
}
