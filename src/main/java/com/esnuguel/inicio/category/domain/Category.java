package com.esnuguel.inicio.category.domain;

import java.util.List;

import com.esnuguel.inicio.product.domain.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//Relación mucho a muchos
@Data
@Builder
public class Category {
    private Long id;
    private String name;
    private List<Product> products;
}
