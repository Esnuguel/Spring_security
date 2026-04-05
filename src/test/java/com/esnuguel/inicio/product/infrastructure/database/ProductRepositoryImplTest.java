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

import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;
import com.esnuguel.inicio.product.infrastructure.database.mapper.ProductEntityMapper;
import com.esnuguel.inicio.product.infrastructure.database.repository.QueryProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryImplTest {
    @Mock
    private QueryProductRepository repository;
    @Mock
    private ProductEntityMapper productEntityMapper;
    @InjectMocks
    private ProductRepositoryImpl productRepositoryImpl;

    @Test
    void shouldNotReturnProductWhenFound(){
        Optional<Product> optionalProduct=productRepositoryImpl.findById(1L);
        assertTrue(optionalProduct.isEmpty());
        
    }
    @Test
    void shouldReturnProductWhenFound(){
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(new ProductEntity()));
        when(productEntityMapper.mapToProduct(any(ProductEntity.class))).thenReturn(Product.builder().id(1L).build());
        Optional<Product> optionalProduct=productRepositoryImpl.findById(1L);
        assertTrue(optionalProduct.isPresent());
        
    }

}
