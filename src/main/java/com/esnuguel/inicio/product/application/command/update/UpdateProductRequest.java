package com.esnuguel.inicio.product.application.command.update;

import org.springframework.web.multipart.MultipartFile;

import com.esnuguel.inicio.common.mediator.Request;

import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {
    private String name;
    private String description;
    private Double price;
    private Long id;
    private MultipartFile file;
}
