package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.requests.PriceRequest;
import com.arstansubanov.cinematica.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {

    private final PriceService priceService;


    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping
    public List<PriceDTO> getAll(){
        return priceService.findAll();
    }

    @GetMapping("/getById")
    public PriceDTO getById(@RequestParam @Valid int id){
        return priceService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid PriceRequest priceRequest){
        return priceService.create(priceRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid PriceRequest priceRequest){
        return priceService.updatePrice(id, priceRequest);
    }

    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        priceService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
