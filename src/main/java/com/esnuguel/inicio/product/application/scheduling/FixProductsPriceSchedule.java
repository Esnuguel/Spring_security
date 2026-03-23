package com.esnuguel.inicio.product.application.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esnuguel.inicio.product.domain.port.ProductoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FixProductsPriceSchedule {

    private final ProductoRepository productoRepository;

    @Scheduled(fixedRate = 6000) //cada 6 seg se ejecuta esto :v
    public void fixProductsPrice(){
        log.info("Fixing products price");
        productoRepository.findAll().forEach(p->{
            p.setPrice(p.getPrice()+1);
            productoRepository.uppsert(p);
        });
        log.info("Finished products ptice");
    }

    

}
