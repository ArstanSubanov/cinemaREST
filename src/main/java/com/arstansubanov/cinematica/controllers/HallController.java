package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.HallDTO;
import com.arstansubanov.cinematica.services.impl.HallServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hall")
public class HallController {

    private final HallServiceImpl hallService;

    @Autowired
    public HallController(HallServiceImpl hallService) {
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
}
