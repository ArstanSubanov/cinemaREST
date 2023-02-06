package com.arstansubanov.cinematica;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinematicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CinematicaApplication.class, args);
    }

//    @Bean
//    public ModelMapper modelMapper(){
//        ModelMapper modelMapper = new ModelMapper();
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
//        return modelMapper;
//    }

}
