package com.esnuguel.inicio.user.application.command.login;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.common.application.mediator.RequestHandler;
@Service
public class LoginUserHandler implements RequestHandler<LoginUserRequest, LoginUserResponse> {

    @Override
    public Class<LoginUserRequest> getRequestType() {
        return LoginUserRequest.class;
    }

    @Override
    public LoginUserResponse handle(LoginUserRequest request) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
