package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.UserDTO;
import com.arstansubanov.cinematica.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Пользователь", description = "API для пользователя")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Вывод всех пользователей")
    @GetMapping("/all")
    public List<UserDTO> getAll(){
        return userService.findAll();
    }

    @Operation(summary = "Вывод пользователя по ID")
    @GetMapping("/getById")
    public UserDTO getById(@RequestParam @Valid int id){
        return userService.findById(id);
    }

    @Operation(summary = "Добавление пользователя")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO){
        userService.save(userDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @Operation(summary = "Обновление пользователя")
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid UserDTO userDTO){
        userService.update(id, userDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
