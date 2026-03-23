package com.esnuguel.inicio.product.infrestructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrestructure.database.entity.ProductEntity;
//Se encarga de Mapear las clases de una a otra de dominio a interface
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductEntityMapper {
    
    ProductEntity mapToProductEntity(Product product);
    Product mapToProduct(ProductEntity productEntity);
}
