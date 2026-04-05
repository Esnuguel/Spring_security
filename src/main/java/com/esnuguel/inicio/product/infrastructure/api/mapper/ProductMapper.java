package com.esnuguel.inicio.product.infrastructure.api.mapper;

import java.util.List;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.category.domain.Category;
import com.esnuguel.inicio.product.application.command.create.CreateProductRequest;
import com.esnuguel.inicio.product.application.command.update.UpdateProductRequest;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrastructure.api.dto.CreateProductDto;
import com.esnuguel.inicio.product.infrastructure.api.dto.ProductDto;
import com.esnuguel.inicio.product.infrastructure.api.dto.ReviewDto;
import com.esnuguel.inicio.product.infrastructure.api.dto.UpdateProductDto;
import com.esnuguel.inicio.review.domain.Review;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR )
public interface ProductMapper {
    CreateProductRequest mapToCreateProductRequest(CreateProductDto createProductoDto);
    UpdateProductRequest mapToUpdateProductRequest(UpdateProductDto updateProductDto);

    @Mapping(target = "provider", source = "productDetail.provider")
    ProductDto mapToProductDto(Product product);

    @Mapping(target = "product", ignore = true)
    Review mapToReview(ReviewDto reviewDto);

    default List<String> mapToCategoryNames(List<Category> categories) {
        return categories.stream().map(Category::getName).toList();
    }
}
