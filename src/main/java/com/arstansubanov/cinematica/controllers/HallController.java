package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.requests.HallRequest;
import com.arstansubanov.cinematica.services.HallService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Зал", description = "API зала")
@RestController
@RequestMapping("api/v1/hall")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @Operation(summary = "Вывод всех залов")
    @GetMapping("/all")
    public List<HallDTO> getHalls(){
        return hallService.findAll();
    }

    @Operation(summary = "Вывод зала по ID")
    @GetMapping("/getById")
    public HallDTO getHallById(@RequestParam @Valid int id){
        return hallService.findById(id);
    }

    @Operation(summary = "Добавление нового зала")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody HallRequest hallRequest){
        return hallService.create(hallRequest);
    }

    @Operation(summary = "Изменение зала")
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid HallRequest hallRequest){
        return hallService.updateHall(id, hallRequest);
    }

    @Operation(summary = "Удаление зала")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        hallService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
