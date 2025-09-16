package com.salas.mongodb.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class CategoryDto {

    private String id;
    private String name;
    private String description;
}
