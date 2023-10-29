package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
    private Double price;
    private String brand;
    private String model;
    private Long categoryId;
}
