package com.esnuguel.inicio.product.domain.entity;

import java.time.Instant;
import java.util.List;

import com.esnuguel.inicio.category.domain.Category;
import com.esnuguel.inicio.productDetail.domain.ProductDetail;
import com.esnuguel.inicio.productDetail.infrastructure.ProductDetailEntity;
import com.esnuguel.inicio.review.domain.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;

    private ProductDetail productDetail;
    private List<Review> reviews;
    private List<Category> categories;

    //private Instant createdAt;
    //private Instant updatedAt;
}
