package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.PriceDTO;
import com.arstansubanov.cinematica.requests.PriceRequest;
import com.arstansubanov.cinematica.services.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Цены", description = "API для цен")
@RestController
@RequestMapping("api/v1/price")
public class PriceController {

    private final PriceService priceService;


    @Autowired
    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @Operation(summary = "Вывод всех цен")
    @GetMapping("/all")
    public List<PriceDTO> getAll(){
        return priceService.findAll();
    }

    @Operation(summary = "Вывод цены по ID")
    @GetMapping("/getById")
    public PriceDTO getById(@RequestParam @Valid int id){
        return priceService.findById(id);
    }

    @Operation(summary = "Добавление цены")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid PriceRequest priceRequest){
        return priceService.create(priceRequest);
    }

    @Operation(summary = "Обновление цены")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid PriceRequest priceRequest){
        return priceService.updatePrice(id, priceRequest);
    }

    @Operation(summary = "Удаление цены")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        priceService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
