package com.esnuguel.inicio.product.infrestructure.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.esnuguel.inicio.product.infrestructure.api.dto.CreateProductDto;
import com.esnuguel.inicio.product.infrestructure.api.dto.ProductDto;
import com.esnuguel.inicio.product.infrestructure.api.dto.UpdateProductDto;

public interface ProductApi {
    ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize);

    ResponseEntity<ProductDto> getProdcutById(@PathVariable long id);

    ResponseEntity<Void> saveProduct(@RequestBody CreateProductDto productDto);

    ResponseEntity<Void> updateProductById(@RequestBody UpdateProductDto productDto);

    ResponseEntity<Void> deleteProduct(@PathVariable Long id);
}
