package com.esnuguel.inicio.user.infrastructure.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.esnuguel.inicio.user.application.command.login.LoginUserRequest;
import com.esnuguel.inicio.user.application.command.login.LoginUserResponse;
import com.esnuguel.inicio.user.application.command.register.RegisterUserRequest;
import com.esnuguel.inicio.user.application.command.register.RegisterUserResponse;
import com.esnuguel.inicio.user.infrastructure.api.dto.LoginRequestDto;
import com.esnuguel.inicio.user.infrastructure.api.dto.RegisterRequestDto;
import com.esnuguel.inicio.user.infrastructure.api.dto.TokenResponseDto;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
    LoginUserRequest mapToLoginUserRequest(LoginRequestDto loginRequestDto);

    RegisterUserRequest mapToRegisterUserRequest(RegisterRequestDto registerRequestDto);

    TokenResponseDto mapToTokenResponseDto(LoginUserResponse loginUserResponse);

    TokenResponseDto mapToTokenResponseDto(RegisterUserResponse registerUserResponse);



}
