package com.esnuguel.inicio.product.infrestructure.database.entity;

import lombok.Builder;
import lombok.Data;
//Entidad de la base de datos xd
@Builder
@Data
public class ProductEntity {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
}
