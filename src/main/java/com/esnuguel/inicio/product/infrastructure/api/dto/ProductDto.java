package com.esnuguel.inicio.product.infrastructure.api.dto;
import java.util.List;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String provider;
    private List<ReviewDto> reviews;
    private List<String> categories;
}
