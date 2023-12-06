package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service){
        this.service = service;
    }

   @GetMapping
    public ResponseEntity<List<AccountDto>> getAccounts() {
        try {
            List<AccountDto> lista = service.getAccounts();
            return ResponseEntity.status(HttpStatus.OK).body(lista);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    }

  
   @PostMapping("/{idUser}")
    public ResponseEntity<?> createAccount(@PathVariable Long idUser, @RequestBody AccountDto dto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(idUser, dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        try {
            String deletionResult = service.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletionResult);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
