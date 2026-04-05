package com.esnuguel.inicio.product.application.command.update;

import org.springframework.web.multipart.MultipartFile;

import com.esnuguel.inicio.common.application.mediator.Request;
import com.esnuguel.inicio.review.domain.Review;

import lombok.Data;

@Data
public class UpdateProductRequest implements Request<Void> {
    private String name;
    private String description;
    private Double price;
    private Long id;
    private String provider;
    private Review review;
    private Long categoryId;
}
