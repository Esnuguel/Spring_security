package com.esnuguel.inicio.common.config;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Spring Boot Web API",
        version = "1.0.0",
        contact = @Contact(
            name = "Esnuguel", email = "esnuguel2.0@gmail.com", url = "https://mtvdeveloper.com"
        ),
        license = @License(
            name = "Apache 2.0", url = "https://www.apache.org/licences/LICENSE-2.0"
        )
    ),
    servers = @Server(
        url = "http://localhost:8082",
        description = "Production"
    )
)

@Configuration
public class OpenAPIConfig {
    
}
