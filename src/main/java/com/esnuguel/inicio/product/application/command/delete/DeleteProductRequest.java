package com.esnuguel.inicio.product.application.command.delete;

import com.esnuguel.inicio.common.mediator.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class DeleteProductRequest implements Request<Void> {
    private Long id;
}
