package com.esnuguel.inicio.product.infrestructure.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.esnuguel.inicio.common.mediator.Mediator;
import com.esnuguel.inicio.product.application.query.getAll.GetAllProductRequest;
import com.esnuguel.inicio.product.application.query.getAll.GetAllProductResponse;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.infrestructure.api.dto.ProductDto;
import com.esnuguel.inicio.product.infrestructure.api.mapper.ProductMapper;
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    @InjectMocks
    private ProductController productController;
    @Mock
    private Mediator mediator;

    @Mock
    private ProductMapper productMapper;

    @Test
    public void getAllProducts(){

        GetAllProductResponse getAllProductResponse=new GetAllProductResponse(List.of(
            Product.builder().id(1L).build(),
            Product.builder().id(2L).build()
        ));

        when(mediator.dispatch(new GetAllProductRequest())).thenReturn(getAllProductResponse);

        ProductDto productDto= new ProductDto();
        productDto.setId(1L);

        when(productMapper.mapToProductDto(any(Product.class))).thenReturn(productDto);

        ResponseEntity<List<ProductDto>> response=productController.getAllProducts("5");
        //Assertions.assertEquals(HttpStatus.OK, response.getStatusCode()); //otra forma
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        List<ProductDto> products=response.getBody();
        assertEquals(2, products.size());
    }


}
