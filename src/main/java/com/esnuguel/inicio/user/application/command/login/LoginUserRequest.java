package com.esnuguel.inicio.user.application.command.login;

import com.esnuguel.inicio.common.application.mediator.Request;


import lombok.Data;
@Data
public class LoginUserRequest implements Request<LoginUserResponse>{
    private String email;
    private String password;
}
