package com.esnuguel.inicio.user.application.command.register;

import org.springframework.stereotype.Service;

import com.esnuguel.inicio.common.application.mediator.RequestHandler;
@Service
public class RegisterUserHandler implements RequestHandler<RegisterUserRequest, RegisterUserResponse> {

    @Override
    public Class<RegisterUserRequest> getRequestType() {
        return RegisterUserRequest.class;
    }

    @Override
    public RegisterUserResponse handle(RegisterUserRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    
    
}
