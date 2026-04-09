package com.esnuguel.inicio.user.infrastructure.api;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esnuguel.inicio.common.application.mediator.Mediator;
import com.esnuguel.inicio.common.infrastructure.service.JwtService;
import com.esnuguel.inicio.user.application.command.login.LoginUserRequest;
import com.esnuguel.inicio.user.application.command.login.LoginUserResponse;
import com.esnuguel.inicio.user.application.command.register.RegisterUserRequest;
import com.esnuguel.inicio.user.application.command.register.RegisterUserResponse;
import com.esnuguel.inicio.user.infrastructure.api.dto.LoginRequestDto;
import com.esnuguel.inicio.user.infrastructure.api.dto.RegisterRequestDto;
import com.esnuguel.inicio.user.infrastructure.api.dto.TokenResponseDto;
import com.esnuguel.inicio.user.infrastructure.api.mapper.UserMapper;

import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Controller", description = "Endpoints for user management")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    
    private final Mediator mediator;
    private final UserMapper userMapper;
    


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        LoginUserRequest request = userMapper.mapToLoginUserRequest(loginRequestDto);
        LoginUserResponse response=mediator.dispatch(request);
        TokenResponseDto tokenResponseDto= userMapper.mapToTokenResponseDto(response);
        return ResponseEntity.ok(tokenResponseDto);
    }

    @PostMapping("/regsiter")
    public ResponseEntity<TokenResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto){
        RegisterUserRequest request= userMapper.mapToRegisterUserRequest(registerRequestDto);
        RegisterUserResponse response=mediator.dispatch(request);
        TokenResponseDto tokenResponseDto= userMapper.mapToTokenResponseDto(response);
        return ResponseEntity.ok(tokenResponseDto);
    }
}
