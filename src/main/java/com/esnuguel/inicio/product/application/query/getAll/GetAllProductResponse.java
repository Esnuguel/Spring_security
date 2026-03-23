package com.esnuguel.inicio.product.application.query.getAll;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

import com.esnuguel.inicio.product.domain.entity.Product;

@Data
@Builder
@AllArgsConstructor
public class GetAllProductResponse {
    private List<Product> products;
}
