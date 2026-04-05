package com.esnuguel.inicio.review.domain;

import com.esnuguel.inicio.product.domain.entity.Product;

import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private Long id;
    private String comment;
    private Integer score;

    private Product product;
}
