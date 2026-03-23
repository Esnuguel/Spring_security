package com.esnuguel.inicio.product.domain.port;

import java.util.List;
import java.util.Optional;

import com.esnuguel.inicio.product.domain.entity.Product;

public interface ProductoRepository {
    void uppsert(Product product);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    void deleteById(Long id);
}
