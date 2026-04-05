package com.esnuguel.inicio.product.infrastructure.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.esnuguel.inicio.common.domain.PaginationQuery;
import com.esnuguel.inicio.common.domain.PaginationResult;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.entity.ProductFilter;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;
import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;
import com.esnuguel.inicio.product.infrastructure.database.mapper.ProductEntityMapper;
import com.esnuguel.inicio.product.infrastructure.database.repository.QueryProductRepository;
import com.esnuguel.inicio.product.infrastructure.database.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;
//Esta clase es la implementacion para usar en una base de datos bajo el contrato de producto Repository
// del dominio
@Repository
@RequiredArgsConstructor

public class ProductRepositoryImpl implements ProductoRepository{

    private final QueryProductRepository repository;

    private final ProductEntityMapper productEntityMapper;


    @Override
    public Product uppsert(Product product) {
        ProductEntity productEntity=productEntityMapper.mapToProductEntity(product);
        ProductEntity save =repository.save(productEntity);
        return productEntityMapper.mapToProduct(save);
    }

    @Cacheable(value = "productos",key = "#id")
    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(productEntityMapper::mapToProduct);
    }

    @Override
    public PaginationResult<Product> findAll(PaginationQuery paginationQuery, ProductFilter productFilter) {
        PageRequest pageRequest=PageRequest.of(paginationQuery.getPage(), paginationQuery.getSize(),
        Sort.by(Sort.Direction.fromString(paginationQuery.getSortDirection()),paginationQuery.getSortBy()));
        
        Specification<ProductEntity> specification=Specification.allOf(
            ProductSpecification.byName(productFilter.getName())
            .and(ProductSpecification.byDescription(productFilter.getDescription()))
            .and(ProductSpecification.byPrice(productFilter.getMinPrice(), productFilter.getMaxPrice()))
        );

        Page<ProductEntity> page=repository.findAll(specification,pageRequest);


        return new PaginationResult<>(
            page.getContent().stream().map(productEntityMapper::mapToProduct).toList(),
            page.getNumber(), 
            page.getSize(), 
            page.getTotalPages(), 
            page.getTotalElements());
    }

    @Override
    @CacheEvict(value = "productos",key = "#id")
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}