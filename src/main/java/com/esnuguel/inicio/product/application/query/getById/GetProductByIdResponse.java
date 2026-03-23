package com.esnuguel.inicio.product.application.query.getById;

import com.esnuguel.inicio.product.domain.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class GetProductByIdResponse {
    private Product product;
}
