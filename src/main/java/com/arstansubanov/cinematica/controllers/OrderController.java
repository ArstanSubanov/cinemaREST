package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.OrderDTO;
import com.arstansubanov.cinematica.requests.OrderRequest;
import com.arstansubanov.cinematica.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAll(){
        return orderService.findAll();
    }

    @GetMapping("/getById")
    public OrderDTO getById(@RequestParam @Valid int id){
        return orderService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid OrderRequest orderRequest){
        return orderService.update(id, orderRequest);
    }

    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        orderService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
