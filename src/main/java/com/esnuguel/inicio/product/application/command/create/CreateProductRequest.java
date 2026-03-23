package com.esnuguel.inicio.product.application.command.create;

import org.springframework.web.multipart.MultipartFile;

import com.esnuguel.inicio.common.mediator.Request;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
public class CreateProductRequest implements Request<Void> {
    private String name;
    private String description;
    private Double price;
    private Long id;
    private MultipartFile file;
}
