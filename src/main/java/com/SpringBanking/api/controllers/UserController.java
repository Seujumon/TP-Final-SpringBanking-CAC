package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBanking.api.exceptions.UserNotExistsException;
import com.SpringBanking.api.models.dto.UserDto;
import com.SpringBanking.api.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ResponseError responseError;

    public UserController(UserService _userService, ResponseError responseError) {

        this.userService = _userService;
        this.responseError=responseError;
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.findAll());
            
        } catch (Exception e) {
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseError);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        try{
            return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.findById(id));
        }catch(UserNotExistsException e){
            responseError.setError("NOT_FOUND");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseError);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        try {
            return ResponseEntity
                    .ok()
                    .body(userService.save(userDto));
        } catch (Exception e) {
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(responseError);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){

        try{
            userService.deleteById(id);

            return ResponseEntity
            .status(HttpStatus.OK)
            .body("El Usuario con id: "+id+" ha sido eliminado!");
        } catch(UserNotExistsException e){
            responseError.setError("NOT_FOUND");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(responseError);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable Long id, @RequestBody UserDto userDto){
        try{
            UserDto userModify = userService.updateUser(id, userDto);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(userModify);
        } catch(Exception e){
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(responseError);
        }
    }
}
