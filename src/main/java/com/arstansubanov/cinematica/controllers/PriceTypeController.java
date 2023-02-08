package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.PriceTypeDTO;
import com.arstansubanov.cinematica.services.PriceTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Вид билета", description = "API для вида билета")
@RestController
@RequestMapping("/api/v1/priceType")
public class PriceTypeController {

    private final PriceTypeService priceTypeService;

    @Autowired
    public PriceTypeController(PriceTypeService priceTypeService) {
        this.priceTypeService = priceTypeService;
    }

    @Operation(summary = "Вывод всех видов билета")
    @GetMapping("/all")
    public List<PriceTypeDTO> getAll(){
        return priceTypeService.findAll();
    }

    @Operation(summary = "Вывод вида билета по ID")
    @GetMapping("/getById")
    public PriceTypeDTO getById(@RequestParam @Valid int id){
        return priceTypeService.findById(id);
    }

    @Operation(summary = "Добавление нового вида билета")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid PriceTypeDTO priceTypeDTO){
        priceTypeService.save(priceTypeDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(summary = "Изменение вида билета")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid PriceTypeDTO priceTypeDTO){
        priceTypeService.update(id, priceTypeDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление вида билета")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(int id){
        priceTypeService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
