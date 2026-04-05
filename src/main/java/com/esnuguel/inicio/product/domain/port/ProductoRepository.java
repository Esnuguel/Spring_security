package com.esnuguel.inicio.product.domain.port;

import java.util.List;
import java.util.Optional;

import com.esnuguel.inicio.common.domain.PaginationQuery;
import com.esnuguel.inicio.common.domain.PaginationResult;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.entity.ProductFilter;

public interface ProductoRepository {
    Product uppsert(Product product);

    Optional<Product> findById(Long id);

    PaginationResult<Product> findAll(PaginationQuery paginationQuery,ProductFilter productFilter);

    void deleteById(Long id);
}
