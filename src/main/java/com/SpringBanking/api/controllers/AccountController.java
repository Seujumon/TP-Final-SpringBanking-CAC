package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService service;

  public AccountController(AccountService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<AccountDto>> getAccounts() {
    List<AccountDto> lista = service.getAccounts();
    return ResponseEntity.status(HttpStatus.OK).body(lista);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getAccount(@PathVariable Long id) {
    AccountDto account = service.getAccountById(id);

    if (account != null) {

      return ResponseEntity.status(HttpStatus.OK).body(account);
      
    } else {
    
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la cuenta con ID: " + id);
    
    }

  }

  @PostMapping
  public ResponseEntity<?> createAccount(@RequestBody AccountDto dto) {
    try {
      
        AccountDto createdAccount = service.createAccount(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        
    } catch (Exception e) {
      
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al crear la cuenta");
        
    }
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, dto));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(service.deleteAccount(id));
  }
}
