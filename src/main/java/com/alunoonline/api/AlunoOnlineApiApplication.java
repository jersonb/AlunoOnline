package com.alunoonline.api;

import com.alunoonline.api.configs.SwaggerConfiguration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Aluno Online", version = "1", description = "API do Jersinho"))
public class AlunoOnlineApiApplication {
    public static void main(String[] args) {
        var application = new SpringApplication(AlunoOnlineApiApplication.class);
        application.addListeners(new SwaggerConfiguration());
        SpringApplication.run(AlunoOnlineApiApplication.class, args);

    }

}
