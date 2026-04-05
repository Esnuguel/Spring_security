package com.esnuguel.inicio.productDetail.infrastructure;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.tomcat.autoconfigure.TomcatServerProperties.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.esnuguel.inicio.product.infrastructure.database.repository.QueryProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.var;

@Component
@RequiredArgsConstructor
public class ProductDetailSeeder implements CommandLineRunner {
    private final QueryProductDetailRepository repository;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;
    @Override
    public void run(String... args) throws Exception {
        long count = repository.count();
        if(count==0){
            var resource=resourceLoader.getResource("classpath:products_details.json");
            List<ProductDetailEntity> productDetails=objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
                
            });
            repository.saveAll(productDetails);
        }
    }


}
