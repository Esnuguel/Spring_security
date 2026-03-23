package com.esnuguel.inicio.product.domain.exception;

public class ProductNotFounException extends RuntimeException {
    public ProductNotFounException(Long id){
        super("El producto con el id "+id+" no fue encontrado");
    }
}
