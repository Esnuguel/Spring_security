package com.esnuguel.inicio.product.infrastructure.api.dto;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateProductDto {
    @NotBlank
    private String name;
    @Length(min = 10,max = 255,message = "Descripcion debe ser con un valor entre 10 y 255 caracteres")
    private String description;
    @DecimalMin(value = "0.01",inclusive = false)
    @DecimalMax(value = "999.99",inclusive = false)
    private Double price;
    private MultipartFile file;
}
