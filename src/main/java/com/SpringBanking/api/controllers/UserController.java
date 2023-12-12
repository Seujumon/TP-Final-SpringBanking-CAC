package com.SpringBanking.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBanking.api.models.dto.UserDto;
import com.SpringBanking.api.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService _userService) {
        userService = _userService;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.findAll());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
            return ResponseEntity
                    .ok()
                    .body(userService.save(userDto));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
            userService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Cuenta con id: " + id + " ha sido eliminada!");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable Long id, @RequestBody UserDto userDto){
        UserDto userModify = userService.updateUser(id, userDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userModify);
    }
}
