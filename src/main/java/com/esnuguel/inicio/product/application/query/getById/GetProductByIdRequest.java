package com.esnuguel.inicio.product.application.query.getById;

import com.esnuguel.inicio.common.mediator.Request;
import com.esnuguel.inicio.product.domain.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class GetProductByIdRequest implements Request<GetProductByIdResponse> {
    private Long id;
}
