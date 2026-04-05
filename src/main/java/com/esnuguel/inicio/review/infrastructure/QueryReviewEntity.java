package com.esnuguel.inicio.review.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QueryReviewEntity extends JpaRepository<ReviewEntity, Long> {
    
}
