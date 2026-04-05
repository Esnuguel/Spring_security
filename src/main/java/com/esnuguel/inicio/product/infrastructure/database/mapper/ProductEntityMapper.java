package com.esnuguel.inicio.product.infrastructure.database.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.category.domain.Category;
import com.esnuguel.inicio.category.infrastructure.CategoryEntity;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;
import com.esnuguel.inicio.review.domain.Review;
import com.esnuguel.inicio.review.infrastructure.ReviewEntity;
//Se encarga de Mapear las clases de una a otra de dominio a interface
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {

    @Mapping(target = "productDetail.product", ignore = true)
    ProductEntity mapToProductEntity(Product product);

    @Mapping(target = "productDetail.product", ignore = true)
    Product mapToProduct(ProductEntity productEntity);

    @Mapping(target = "product", ignore = true)
    Review mapToReview(ReviewEntity reviewEntity);

    @Mapping(target = "product", ignore = true)
    ReviewEntity mapToReviewEntity(Review review);

    @Mapping(target = "products", ignore = true)
    Category mapToCategory(CategoryEntity categoryEntity);

    @Mapping(target = "products", ignore = true)
    CategoryEntity mapToCategoryEntity(Category category);


    @AfterMapping
    default void linkReviews(@MappingTarget ProductEntity productEntity) {
        if (productEntity.getReviews() != null) {
            productEntity.getReviews().forEach(r -> r.setProduct(productEntity));
        }
    }

}
