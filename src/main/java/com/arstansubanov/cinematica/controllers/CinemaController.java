package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.services.impl.CinemaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    private final CinemaServiceImpl cinemaService;

    @Autowired
    public CinemaController(CinemaServiceImpl cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping
    public List<CinemaDTO> getCinemas(){
        return cinemaService.findAll();
    }
}
