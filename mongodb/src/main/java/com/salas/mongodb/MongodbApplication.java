package com.salas.mongodb;

import com.salas.mongodb.product.Product;
import com.salas.mongodb.repo.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            Product product = Product.builder()
                    .name("iphone")
                    .description("good")
                    .build();
            productRepository.insert(product);
        };
    }

}
