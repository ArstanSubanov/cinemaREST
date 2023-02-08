package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.OrderDTO;
import com.arstansubanov.cinematica.requests.OrderRequest;
import com.arstansubanov.cinematica.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Заказ", description = "API заказа")
@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Вывод заказов на предстоящие сеансы")
    @GetMapping("/all")
    public List<OrderDTO> getAll(){
        return orderService.getActiveOrders();
    }

    @Operation(summary = "Вывод заказа по ID")
    @GetMapping("/getById")
    public OrderDTO getById(@RequestParam @Valid int id){
        return orderService.findById(id);
    }

    @Operation(summary = "Добавление нового заказа")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid OrderRequest orderRequest){
        return orderService.create(orderRequest);
    }

    @Operation(summary = "Обновление заказа")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid OrderRequest orderRequest){
        return orderService.update(id, orderRequest);
    }

    @Operation(summary = "Удаление заказа")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        orderService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
