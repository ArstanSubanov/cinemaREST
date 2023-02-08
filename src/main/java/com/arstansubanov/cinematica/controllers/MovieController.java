package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.MovieDTO;
import com.arstansubanov.cinematica.requests.MovieByIdAndDateRequest;
import com.arstansubanov.cinematica.responses.MovieResponse;
import com.arstansubanov.cinematica.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Фильм", description = "API фильма")
@RestController
@RequestMapping("api/v1/movie")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Вывод всех активных фильмов")
    @GetMapping("/active")
    public List<MovieDTO> getAllActive(){
        return movieService.getAllActiveMovies();
    }

    @Operation(summary = "Вывод всех фильмов")
    @GetMapping("/all")
    public List<MovieDTO> getAll(@RequestParam int limit, @RequestParam int offset){
        return movieService.getMoviesByOffsetAndLimit(offset, limit);
    }

    @Operation(summary = "Вывод фильма по ID")
    @GetMapping("/getById")
    public MovieDTO getById(@RequestParam @Valid int id){
        return movieService.findById(id);
    }

    @Operation(summary = "Добавление фильма")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid MovieDTO movieDTO){
        movieService.save(movieDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(summary = "Обновление фильма")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody MovieDTO movieDTO){
        movieService.update(id, movieDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление фильма")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        movieService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Вывод сеансов фильма по ID и дате")
    @PostMapping("/getSession")
    public MovieResponse getMovieSessionByMovie(@RequestBody @Valid MovieByIdAndDateRequest movieByIdAndDateRequest){
        return movieService.getMovieById(movieByIdAndDateRequest);
    }


}
