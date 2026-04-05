package com.esnuguel.inicio.product.application.query.getAll;

import com.esnuguel.inicio.common.application.mediator.Request;
import com.esnuguel.inicio.common.domain.PaginationQuery;
import com.esnuguel.inicio.product.domain.entity.ProductFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllProductRequest implements Request<GetAllProductResponse>{
    PaginationQuery paginationQuery;
    ProductFilter productFilter;
}