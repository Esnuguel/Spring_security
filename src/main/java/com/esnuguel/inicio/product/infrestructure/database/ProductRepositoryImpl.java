package com.esnuguel.inicio.product.infrestructure.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;
import com.esnuguel.inicio.product.infrestructure.database.entity.ProductEntity;
import com.esnuguel.inicio.product.infrestructure.database.mapper.ProductEntityMapper;

import lombok.RequiredArgsConstructor;
//Esta clase es la implementacion para usar en una base de datos bajo el contrato de producto Repository
// del dominio
@Repository
@RequiredArgsConstructor

public class ProductRepositoryImpl implements ProductoRepository{

    private final List<ProductEntity> products= new ArrayList<>();

    private final ProductEntityMapper productEntityMapper;


    @Override
    public void uppsert(Product product) {
        ProductEntity productEntity=productEntityMapper.mapToProductEntity(product);
        products.removeIf(p->p.getId().equals(product.getId()));
        products.add(productEntity);
    }

    @Cacheable(value = "productos",key = "#id")
    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(p-> p.getId().equals(id)).findFirst().map(productEntityMapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        return products.stream().map(productEntityMapper::mapToProduct).toList();
    }

    @Override
    @CacheEvict(value = "productos",key = "#id")
    public void deleteById(Long id) {
        products.removeIf(p->p.getId().equals(id));
    }

}