package com.esnuguel.inicio.product.infrastructure.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.esnuguel.inicio.common.domain.PaginationResult;
import com.esnuguel.inicio.product.infrastructure.api.dto.CreateProductDto;
import com.esnuguel.inicio.product.infrastructure.api.dto.ProductDto;
import com.esnuguel.inicio.product.infrastructure.api.dto.UpdateProductDto;

public interface ProductApi {
    ResponseEntity<PaginationResult<ProductDto>> getAllProducts(int pageNumber, int pageSize, String sortBy, String sortDirection, String name, String description, Double minPrice, Double maxPrice);

    ResponseEntity<ProductDto> getProdcutById(@PathVariable long id);

    ResponseEntity<Void> saveProduct(@RequestBody CreateProductDto productDto);

    ResponseEntity<Void> updateProductById(@RequestBody UpdateProductDto productDto);

    ResponseEntity<Void> deleteProduct(@PathVariable Long id);
}
