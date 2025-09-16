package com.salas.mongodb.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@Builder
@EqualsAndHashCode
public class ProductDto {

    private String id;
    private String name;
    private String description;
    private List<String> tags;
    private CategoryDto category;
}
