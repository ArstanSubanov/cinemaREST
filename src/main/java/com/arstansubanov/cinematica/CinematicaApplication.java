package com.arstansubanov.cinematica;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinematicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinematicaApplication.class, args);
    }

}
