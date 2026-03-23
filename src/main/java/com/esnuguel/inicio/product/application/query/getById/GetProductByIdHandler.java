package com.esnuguel.inicio.product.application.query.getById;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.common.mediator.RequestHandler;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.exception.ProductNotFounException;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GetProductByIdHandler implements RequestHandler<GetProductByIdRequest,GetProductByIdResponse> {
    private final ProductoRepository productoRepository;

    @Override
    public Class<GetProductByIdRequest> getRequestType() {
        return GetProductByIdRequest.class;
    }

    @Override
    public GetProductByIdResponse handle(GetProductByIdRequest request) {
        Product product=productoRepository.findById(request.getId()).orElseThrow(()-> new ProductNotFounException(request.getId()));
        return new GetProductByIdResponse(product);
    }
    
}
