package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.requests.HallRequest;
import com.arstansubanov.cinematica.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping
    public List<HallDTO> getHalls(){
        return hallService.findAll();
    }

    @GetMapping("/getById")
    public HallDTO getHallById(@RequestParam @Valid int id){
        return hallService.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<?> create(@RequestBody HallRequest hallRequest){
        return hallService.create(hallRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid HallRequest hallRequest){
        return hallService.updateHall(id, hallRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        hallService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
