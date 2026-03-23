package com.esnuguel.inicio.product.application.command.delete;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.common.mediator.RequestHandler;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteProductHandler implements RequestHandler<DeleteProductRequest,Void> {

    private final ProductoRepository productoRepository;    

    @Override
    public Void handle(DeleteProductRequest request) {
        System.out.println("eliminando producto con id: "+ request.getId()+".....");
        try {
            Thread.sleep(5000);//es pa prueba xd
        } catch (Exception e) {
            // TODO: handle exception
        }
        productoRepository.deleteById(request.getId());
        System.out.println("Producto eliminado con id: "+ request.getId()+".....");
        return null;
    }

    @Override
    public Class<DeleteProductRequest> getRequestType() {
        return DeleteProductRequest.class;
    }
    
}
