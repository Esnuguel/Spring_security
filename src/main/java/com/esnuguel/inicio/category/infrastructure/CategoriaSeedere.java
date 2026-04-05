package com.esnuguel.inicio.category.infrastructure;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@Component
@AllArgsConstructor
public class CategoriaSeedere implements CommandLineRunner{

    private final ResourceLoader resourceLoader;
    private final QueryCategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count()==0){
            Resource resource=resourceLoader.getResource("classpath:categories.json");
            InputStream is=resource.getInputStream();
            List<CategoryEntity> categoryEntities=new ObjectMapper().readValue(is, new TypeReference<List<CategoryEntity>>(){});
            categoryRepository.saveAll(categoryEntities);
        }

    }
}
