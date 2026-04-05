package com.esnuguel.inicio.productDetail.domain;

import com.esnuguel.inicio.product.domain.entity.Product;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDetail {
    private Long id;
    private String specifications;
    private String warranty;
    private String provider;

    private Product product;
}
