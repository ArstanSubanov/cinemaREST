package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.StatusDTO;
import com.arstansubanov.cinematica.services.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Статус билета", description = "API для статуса билета")
@RestController
@RequestMapping("api/v1/status")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @Operation(summary = "Вывод всех статусов")
    @GetMapping("/all")
    public List<StatusDTO> getAll(){
        return statusService.findAll();
    }

    @Operation(summary = "Вывод статуса по ID")
    @GetMapping("/getById")
    public StatusDTO findById(@RequestParam @Valid int id){
        return statusService.findById(id);
    }

    @Operation(summary = "Добавление нового статуса")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid StatusDTO statusDTO){
        statusService.save(statusDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(summary = "Обновление статуса")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid StatusDTO statusDTO){
        statusService.update(id, statusDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление статуса")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        statusService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
