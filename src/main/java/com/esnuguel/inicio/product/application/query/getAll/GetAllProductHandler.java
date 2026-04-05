package com.esnuguel.inicio.product.application.query.getAll;

import java.util.List;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.common.application.mediator.RequestHandler;
import com.esnuguel.inicio.common.domain.PaginationResult;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class GetAllProductHandler implements RequestHandler<GetAllProductRequest,GetAllProductResponse> {

    private final ProductoRepository productoRepository;
    
    @Override
    public Class<GetAllProductRequest> getRequestType() {
        return GetAllProductRequest.class;
    }

    @Override
    public GetAllProductResponse handle(GetAllProductRequest request) {
        PaginationResult<Product> products=productoRepository.findAll(request.getPaginationQuery(),request.getProductFilter());
        return new GetAllProductResponse(products);
    }
    
}
