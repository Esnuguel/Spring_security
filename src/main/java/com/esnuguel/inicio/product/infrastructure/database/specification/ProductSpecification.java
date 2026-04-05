package com.esnuguel.inicio.product.infrastructure.database.specification;

import org.springframework.data.jpa.domain.Specification;

import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;

public class ProductSpecification {
    public static Specification<ProductEntity> byName(String name){
        return (root, query, criteriaBuilder)->name==null?null: criteriaBuilder.like(root.get("name"), "%"+name+"%");

    }

    public static Specification<ProductEntity> byDescription(String description){
        return (root, query, criteriaBuilder)->description==null?null: criteriaBuilder.like(root.get("description"), "%"+description+"%");
    }

    public static Specification<ProductEntity> byPrice(Double minPrice, Double maxPrice){
        return (root, query, criteriaBuilder)->{
            if(minPrice==null && maxPrice==null) return null;
            if(minPrice==null) return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            if(maxPrice==null) return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
        };
    }
}
