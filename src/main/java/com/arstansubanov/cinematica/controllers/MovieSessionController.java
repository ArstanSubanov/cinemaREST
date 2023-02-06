package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.MovieSessionDTO;
import com.arstansubanov.cinematica.requests.MovieSessionRequest;
import com.arstansubanov.cinematica.services.MovieSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/seance")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;

    @Autowired
    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @GetMapping
    public List<MovieSessionDTO> getNextMovies(){
        return movieSessionService.findAll();
    }

    @GetMapping("/getById")
    public MovieSessionDTO getById(@RequestParam @Valid int id){
        return movieSessionService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid MovieSessionRequest movieSessionRequest){
        return movieSessionService.create(movieSessionRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid MovieSessionRequest movieSessionRequest){
        return movieSessionService.updateMovieSession(id, movieSessionRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(int id){
        movieSessionService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
