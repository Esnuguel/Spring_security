package com.esnuguel.inicio.category.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryCategoryRepository extends JpaRepository<CategoryEntity, Long> {
    
}
