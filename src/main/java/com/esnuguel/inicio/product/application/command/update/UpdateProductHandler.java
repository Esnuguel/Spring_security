package com.esnuguel.inicio.product.application.command.update;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.common.mediator.RequestHandler;
import com.esnuguel.inicio.common.util.FileUtils;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UpdateProductHandler implements RequestHandler<UpdateProductRequest,Void> {

    private final ProductoRepository productRepository;  
    private final FileUtils fileUtils;

    @Override
    public Void handle(UpdateProductRequest request) {
        String uniqueFileName=fileUtils.saveProductImage(request.getFile());

        Product product= Product.builder()
            .description(request.getDescription())
            .name(request.getName())
            .price(request.getPrice())
            .image(uniqueFileName)
            .id(request.getId())
            .build();
        productRepository.uppsert(product);
        return null;
    }

    @Override
    public Class<UpdateProductRequest> getRequestType() {
        return UpdateProductRequest.class;
    }
    
}
