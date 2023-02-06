package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.StatusDTO;
import com.arstansubanov.cinematica.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDTO> getAll(){
        return statusService.findAll();
    }

    @GetMapping("/getById")
    public StatusDTO findById(@RequestParam @Valid int id){
        return statusService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid StatusDTO statusDTO){
        statusService.save(statusDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid StatusDTO statusDTO){
        statusService.update(id, statusDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        statusService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
