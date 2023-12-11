package com.SpringBanking.api.controllers;

import com.SpringBanking.api.exceptions.AccountNotExistsException;
import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  private final AccountService service;

  public AccountController(AccountService service) {
    this.service = service;
  }



  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getAccount(@PathVariable Long id) {
    try {
      return ResponseEntity.status(HttpStatus.OK).body(service.getAccountById(id));
    } catch (AccountNotExistsException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
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
  

@PutMapping(value = "/{id}")
public ResponseEntity<AccountDto> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
    try {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, dto));
  } catch (Exception e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(service.updateAccount(id, dto));
  }
}
  
  @PostMapping("/{idUser}")
  public ResponseEntity<?> createAccount(@PathVariable Long idUser, @RequestBody AccountDto dto){
      try {
          return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(idUser, dto));
      } catch (Exception e) {
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
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

  //Informa un mal formato en el body 
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> handlerHttpMessageNotReadable(HttpMessageNotReadableException ex){
      return ResponseEntity.badRequest().body("Invalid request body\n" + ex.getMessage());
  }
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<?> handlerPathTypeMissmatch(MethodArgumentTypeMismatchException e){
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}
