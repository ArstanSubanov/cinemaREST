package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.requests.SeatRequest;
import com.arstansubanov.cinematica.responses.SeatResponse;
import com.arstansubanov.cinematica.services.SeatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Место",description = "API для места в зале")
@RestController
@RequestMapping("api/v1/seat")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @Operation(summary = "Вывод всех мест")
    @GetMapping("/all")
    public List<SeatDTO> getAll(){
        return seatService.findAll();
    }

    @Operation(summary = "Вывод места по ID")
    @GetMapping("/getById")
    public SeatDTO getById(@RequestParam @Valid int id){
        return seatService.findById(id);
    }

    @Operation(summary = "Добавление нового места")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid SeatRequest seatRequest){
        return seatService.create(seatRequest);
    }

    @Operation(summary = "Обновление места")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid SeatRequest seatRequest){
        return seatService.updateSeat(id, seatRequest);
    }

    @Operation(summary = "Удаление места")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        seatService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Вывод мест по сеансу с их статусом")
    @GetMapping("/stat")
    public List<SeatResponse> getAllSeatsWithStatus(@RequestParam @Valid int movieSessionId){
        return seatService.getSeatResponses(movieSessionId);
    }
}
