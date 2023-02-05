package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieDTO> getAllActive(){
        return movieService.getAllActiveMovies();
    }

    @GetMapping("/all")
    public List<MovieDTO> getAll(){
        return movieService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid MovieDTO movieDTO){
        movieService.save(movieDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody MovieDTO movieDTO){
        movieService.update(id, movieDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        movieService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
