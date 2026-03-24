package com.esnuguel.inicio.product.infrestructure.api;

import java.util.List;
import java.util.Optional;
import java.net.URI;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esnuguel.inicio.common.mediator.Mediator;
import com.esnuguel.inicio.product.application.command.create.CreateProductRequest;
import com.esnuguel.inicio.product.application.command.delete.DeleteProductRequest;
import com.esnuguel.inicio.product.application.command.update.UpdateProductRequest;
import com.esnuguel.inicio.product.application.query.getAll.GetAllProductRequest;
import com.esnuguel.inicio.product.application.query.getAll.GetAllProductResponse;
import com.esnuguel.inicio.product.application.query.getById.GetProductByIdRequest;
import com.esnuguel.inicio.product.application.query.getById.GetProductByIdResponse;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrestructure.api.dto.CreateProductDto;
import com.esnuguel.inicio.product.infrestructure.api.dto.ProductDto;
import com.esnuguel.inicio.product.infrestructure.api.dto.UpdateProductDto;
import com.esnuguel.inicio.product.infrestructure.api.mapper.ProductMapper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Product", description = "Product API Operation")
@Slf4j
public class ProductController implements ProductApi {

    private final Mediator mediator;

    private final ProductMapper productMapper;

    @Operation(summary = "Get all products")
    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts(@RequestParam(required = false) String pageSize){
        log.info("Getting all products");
        GetAllProductResponse response=mediator.dispatch(new GetAllProductRequest());
        List<ProductDto> productsDto=response.getProducts().stream().map(productMapper::mapToProductDto).toList();
        log.info("Found {} prodcuts",productsDto.size());

        //Cacheo de info esta dentro de el repo

        return ResponseEntity.ok(productsDto);
    }

    @Operation(summary = "Get product by id",description = "Get product by id xd")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProdcutById(@PathVariable long id){
        GetProductByIdResponse response= mediator.dispatch(new GetProductByIdRequest(id));
        ProductDto productDto=productMapper.mapToProductDto(response.getProduct());
        return ResponseEntity.ok(productDto);
    }
    //Trabajo sobre este :v
    @PostMapping("")
    public ResponseEntity<Void> saveProduct(@ModelAttribute @Valid CreateProductDto productDto){
        CreateProductRequest request = productMapper.mapToCreateProductRequest(productDto);
        mediator.dispatch(request);
        return ResponseEntity.created(URI.create("/api/v1/products/".concat(productDto.getId().toString()))).build();
    }

    @PutMapping("")
    public ResponseEntity<Void> updateProductById(@ModelAttribute @Valid UpdateProductDto productDto){
        UpdateProductRequest request= productMapper.mapToUpdateProductRequest(productDto);
        mediator.dispatch(request);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        DeleteProductRequest request= new DeleteProductRequest(id);
        mediator.asyncDispatch(request);
        return ResponseEntity.noContent().build();
    }

}
