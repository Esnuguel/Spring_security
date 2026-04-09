package com.esnuguel.inicio.user.infrastructure.api.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;
@Data
public class RegisterRequestDto {
    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
