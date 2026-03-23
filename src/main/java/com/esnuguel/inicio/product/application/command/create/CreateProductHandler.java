package com.esnuguel.inicio.product.application.command.create;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.esnuguel.inicio.common.mediator.RequestHandler;
import com.esnuguel.inicio.common.util.FileUtils;
import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateProductHandler implements RequestHandler<CreateProductRequest,Void>{

    private final ProductoRepository productRepository;    
    private final FileUtils fileUtils;
    
    @Override
    public Class<CreateProductRequest> getRequestType() {
        return CreateProductRequest.class;
    }

    @Override
    public Void handle(CreateProductRequest request) {
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
    
}
