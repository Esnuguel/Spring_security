package com.esnuguel.inicio.user.infrastructure.database.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.user.domain.User;
import com.esnuguel.inicio.user.infrastructure.database.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserEntityMapper {

    @Mapping(target = "authorities", ignore = true)
    UserEntity maptoUserEntity(User user);
    User mapToUser(UserEntity userEntity);
}
