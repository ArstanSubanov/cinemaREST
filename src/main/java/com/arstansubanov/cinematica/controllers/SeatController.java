package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.SeatDTO;
import com.arstansubanov.cinematica.requests.SeatRequest;
import com.arstansubanov.cinematica.responses.SeatResponse;
import com.arstansubanov.cinematica.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public List<SeatDTO> getAll(){
        return seatService.findAll();
    }

    @GetMapping("/getById")
    public SeatDTO getById(@RequestParam @Valid int id){
        return seatService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SeatRequest seatRequest){
        return seatService.create(seatRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid SeatRequest seatRequest){
        return seatService.updateSeat(id, seatRequest);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        seatService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/stat")
    public List<SeatResponse> getAllSeatsWithStatus(@RequestParam @Valid int priceId){
        return seatService.getSeatResponses(priceId);
    }
}
