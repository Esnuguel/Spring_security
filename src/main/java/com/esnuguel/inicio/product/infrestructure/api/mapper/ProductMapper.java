package com.esnuguel.inicio.product.infrestructure.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.product.application.command.create.CreateProductRequest;
import com.esnuguel.inicio.product.application.command.update.UpdateProductRequest;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrestructure.api.dto.CreateProductDto;
import com.esnuguel.inicio.product.infrestructure.api.dto.ProductDto;
import com.esnuguel.inicio.product.infrestructure.api.dto.UpdateProductDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR )
public interface ProductMapper {
    CreateProductRequest mapToCreateProductRequest(CreateProductDto createProductoDto);
    UpdateProductRequest mapToUpdateProductRequest(UpdateProductDto updateProductDto);
    ProductDto mapToProductDto(Product product);
}
