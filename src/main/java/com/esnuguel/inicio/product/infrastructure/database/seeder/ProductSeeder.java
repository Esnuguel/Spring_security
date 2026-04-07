package com.esnuguel.inicio.product.infrastructure.database.seeder;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import com.esnuguel.inicio.product.domain.entity.Product;
import com.esnuguel.inicio.product.domain.port.ProductoRepository;
import com.esnuguel.inicio.product.infrastructure.api.mapper.ProductMapper;
import com.esnuguel.inicio.product.infrastructure.database.entity.ProductEntity;
import com.esnuguel.inicio.product.infrastructure.database.mapper.ProductEntityMapper;
import com.esnuguel.inicio.product.infrastructure.database.repository.QueryProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Profile("!test") // Solo se ejecutará en perfiles que no sean "test"
public class ProductSeeder implements CommandLineRunner{

    //private final ProductoRepository productRepository;
    private final QueryProductRepository productRepository;
    //private final ProductEntityMapper productMapper;
    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;


    @Override
    public void run(String... args) throws Exception {
        long count=productRepository.count();
        if(count==0){
            /*
            List<Product> products=List.of(Product.builder().name("Product 1").description("Description 1").price(10.0).image("https://example.com/images/product1.jpg").build(),
                    Product.builder().name("Product 2").description("Description 2").price(20.0).image("https://example.com/images/product2.jpg").build(),
                    Product.builder().name("Product 3").description("Description 3").price(30.0).image("https://example.com/images/product3.jpg").build(),
                    Product.builder().name("Product 4").description("Description 4").price(40.0).image("https://example.com/images/product4.jpg").build(),
                    Product.builder().name("Product 5").description("Description 5").price(50.0).image("https://example.com/images/product5.jpg").build());
            productRepository.saveAll(products.stream().map(productMapper::mapToProductEntity).toList());
            */

            //Otra forma de cargar los datos desde un archivo JSON
            Resource resource=resourceLoader.getResource("classpath:data.json");
            List<ProductEntity> products=objectMapper.readValue(resource.getInputStream(), new TypeReference<>() {
                
            });
            productRepository.saveAll(products);

        }
    }
    
}
