package com.esnuguel.inicio.product.aplication.query.getById;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.esnuguel.inicio.product.application.query.getById.GetProductByIdHandler;
import com.esnuguel.inicio.product.application.query.getById.GetProductByIdRequest;
import com.esnuguel.inicio.product.application.query.getById.GetProductByIdResponse;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.exception.ProductNotFounException;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;

@ExtendWith(MockitoExtension.class)
public class GetProductByIdHandlerTest {

    @InjectMocks
    private GetProductByIdHandler getProductByHandler;

    @Mock
    private ProductoRepository productoRepository;

    @Test
    void shouldReturnProductWhenFound(){
        Long id=1L;
        Product product= Product.builder().id(id).build();
        GetProductByIdRequest request= new GetProductByIdRequest(id);
        when(productoRepository.findById(id)).thenReturn(Optional.of(product));

        GetProductByIdResponse response=getProductByHandler.handle(request);

        assertNotNull(response);
        assertEquals(response.getProduct(), product);
    }

    @Test
    void shouldThrowExceptionWhenProdcutNotFound(){
        Long id=1L;
        GetProductByIdRequest request= new GetProductByIdRequest(id);
        when(productoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ProductNotFounException.class,()->getProductByHandler.handle(request));
    }

}
