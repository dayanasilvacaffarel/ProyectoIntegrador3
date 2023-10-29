package com.example.demo.dto;

import com.example.demo.entity.Image;
import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    private List<Image> images;
}
