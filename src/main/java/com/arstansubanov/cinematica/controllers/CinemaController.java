package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.CinemaDTO;
import com.arstansubanov.cinematica.services.impl.CinemaServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Кинотеатр", description = "API кинотеатра")
@RestController
@RequestMapping("/api/v1/cinema")
public class CinemaController {

    private final CinemaServiceImpl cinemaService;

    @Autowired
    public CinemaController(CinemaServiceImpl cinemaService) {
        this.cinemaService = cinemaService;
    }

    @Operation(summary = "Вывод всех кинотетров")
    @GetMapping("/all")
    public List<CinemaDTO> getCinemas(){
        return cinemaService.findAll();
    }

    @Operation(summary = "Вывод кинотеатра по ID")
    @GetMapping("/getById")
    public CinemaDTO getCinema(@RequestParam int id){
        return cinemaService.findById(id);
    }

    @Operation(summary = "Создание кинотеатра")
    @PostMapping("/create")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid CinemaDTO cinemaDTO){
        cinemaService.save(cinemaDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение кинотеатра")
    @PutMapping("/update")
    public ResponseEntity<HttpStatus> update(@RequestParam int id, @RequestBody @Valid CinemaDTO cinemaDTO){
        cinemaDTO.setId(id);
        cinemaService.save(cinemaDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление кинотеатра")
    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> delete(@RequestParam int id){
        cinemaService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
