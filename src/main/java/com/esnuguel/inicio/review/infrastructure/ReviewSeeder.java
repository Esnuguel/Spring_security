package com.esnuguel.inicio.review.infrastructure;

import java.io.InputStream;
import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Component
@RequiredArgsConstructor
public class ReviewSeeder implements CommandLineRunner{

    private final QueryReviewEntity reviewRepository;

    @Override
    public void run(String... args) throws Exception {
        // Aquí puedes agregar la lógica para cargar datos de reseñas si es necesario
        if(reviewRepository.count()==0){
            Resource resource=new ClassPathResource("reviews.json");
            InputStream is=resource.getInputStream();
            List<ReviewEntity> reviewEntities= new ObjectMapper().readValue(is, new TypeReference<List<ReviewEntity>>(){});
            reviewRepository.saveAll(reviewEntities);
        }

    }
    
}
