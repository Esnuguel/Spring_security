package com.esnuguel.inicio.category.infrastructure;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.category.domain.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.ERROR )
public interface CategoryEntityMapper {
    @Mapping(target = "products", ignore = true)
    Category mapToCategory(CategoryEntity categoryEntity);
}
