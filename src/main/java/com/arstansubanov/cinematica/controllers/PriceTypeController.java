package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.PriceTypeDTO;
import com.arstansubanov.cinematica.services.PriceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/priceType")
public class PriceTypeController {

    private final PriceTypeService priceTypeService;

    @Autowired
    public PriceTypeController(PriceTypeService priceTypeService) {
        this.priceTypeService = priceTypeService;
    }

    @GetMapping
    public List<PriceTypeDTO> getAll(){
        return priceTypeService.findAll();
    }

    @GetMapping("/getById")
    public PriceTypeDTO getById(@RequestParam @Valid int id){
        return priceTypeService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PriceTypeDTO priceTypeDTO){
        priceTypeService.save(priceTypeDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid PriceTypeDTO priceTypeDTO){
        priceTypeService.update(id, priceTypeDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<?> delete(int id){
        priceTypeService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
