package com.esnuguel.inicio.productDetail.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface QueryProductDetailRepository extends JpaRepository<ProductDetailEntity, Long> {
    
}
