package com.arstansubanov.cinematica.controllers;

import com.arstansubanov.cinematica.dto.UserDTO;
import com.arstansubanov.cinematica.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAll(){
        return userService.findAll();
    }

    @GetMapping("/getById")
    public UserDTO getById(@RequestParam @Valid int id){
        return userService.findById(id);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid UserDTO userDTO){
        userService.save(userDTO);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestParam @Valid int id, @RequestBody @Valid UserDTO userDTO){
        userService.update(id, userDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam @Valid int id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
