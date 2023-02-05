package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.services.impl.CinemaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/getById")
    public CinemaDTO getCinema(@RequestParam int id){
        return cinemaService.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CinemaDTO cinemaDTO){
        cinemaService.save(cinemaDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestParam int id, @RequestBody @Valid CinemaDTO cinemaDTO){
        cinemaDTO.setId(id);
        cinemaService.save(cinemaDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam int id){
        cinemaService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
