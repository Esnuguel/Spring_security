package com.esnuguel.inicio.product.infrastructure.database;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.


import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;
import com.esnuguel.inicio.product.infrastructure.database.mapper.ProductEntityMapper;
import com.esnuguel.inicio.product.infrastructure.database.repository.QueryProductRepository;

/* 
@DataJpaTest
class ProductRepositoryImplJpaTest {

    @Autowired
    private QueryProductRepository repository;

    @Test
    void shouldNotReturnProductWhenFound(){
        Optional<ProductEntity> optionalProduct=repository.findById(1L);
        assertTrue(optionalProduct.isEmpty());
        
    }
    @Test
    void shouldReturnProductWhenFound(){
        Optional<ProductEntity> optionalProduct=repository.findById(1L);
        assertTrue(optionalProduct.isPresent());
        
    }

}
*/
