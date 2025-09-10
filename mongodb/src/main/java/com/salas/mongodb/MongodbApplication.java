package com.salas.mongodb;

import com.salas.mongodb.entity.Category;
import com.salas.mongodb.repo.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CategoryRepo categoryRepo) {
        return args -> {
            Category phone = Category.builder()
                    .name("Phone")
                    .description("some description")
                    .build();
            Category car = Category.builder()
                    .description("some description-2")
                    .name("Car")
                    .build();

          categoryRepo.saveAll(List.of(phone, car));
        };
    }
}
