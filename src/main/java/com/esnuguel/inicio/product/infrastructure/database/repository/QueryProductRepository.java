package com.esnuguel.inicio.product.infrastructure.database.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;

@Repository
public interface QueryProductRepository extends JpaRepository<ProductEntity,Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findByNameContaining(String name);
    List<ProductEntity> findAllByPriceIsBetween(Double priceAfter, Double priceBefore);

    @Query("""
            SELECT p 
            FROM ProductEntity p 
            WHERE p.name LIKE concat('%',:name,'%') 
            OR p.description LIKE concat('%',:description,'%')  
            OR p.price BETWEEN :priceAfter AND :priceBefore
        """)
    List<ProductEntity> findProductsDetails(String name,String description, Double priceAfter, Double priceBefore);

    @QueryHints({
        @QueryHint(name = "hibernate.lock.timeout", value = "0")
    })
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<ProductEntity> findTop50ByName(String name);

    boolean existsByName(String name);

    //Paginacion
    Page<ProductEntity> findAll(Specification<ProductEntity> specification, Pageable pageable);

    @EntityGraph(attributePaths = {"productDetail", "reviews", "categories"})
    Optional<ProductEntity> findById(Long id);
}
