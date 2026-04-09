package com.esnuguel.inicio.user.infrastructure.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esnuguel.inicio.user.infrastructure.database.entity.UserEntity;

public interface QueryUserRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findByEmail(String email);

}
