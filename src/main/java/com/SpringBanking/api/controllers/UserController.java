package com.SpringBanking.api.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.UserDto;
import com.SpringBanking.api.services.UserService;

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
        Optional<User> user = userService.findById(id);

        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user.get());
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity
                    .ok()
                    .body(userService.save(userDto));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.toString());
        }
    }

}
