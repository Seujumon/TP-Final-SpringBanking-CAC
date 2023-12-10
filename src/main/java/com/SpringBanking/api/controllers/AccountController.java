package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.models.dto.ResponseError;
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
  private final ResponseError responseError;


  public AccountController(AccountService service, ResponseError responseError) {
      this.responseError=responseError;
      this.service = service;
  }



  @GetMapping(value = "/{id}")
  public ResponseEntity<?> getAccount(@PathVariable Long id) {
        try {
            AccountDto account = service.getAccountById(id);
            return ResponseEntity.status(HttpStatus.OK).body(account);
        }catch(Exception e) {
            responseError.setError("NOT_FOUND");
            responseError.setMessege("No se encontró la cuenta con ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseError);
        }
  }

   @GetMapping
    public ResponseEntity<?> getAccounts() {
        try {
            List<AccountDto> accountsList = service.getAccounts();
            return ResponseEntity.status(HttpStatus.OK).body(accountsList);
        } catch (Exception ex) {
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
        }
    }
  

@PutMapping(value = "/{id}")
public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountDto dto) {
    try {
    return ResponseEntity.status(HttpStatus.OK).body(service.updateAccount(id, dto));
  } catch (Exception e) {
        responseError.setError("BAD_REQUEST");
        responseError.setMessege(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
  }
}
  
  @PostMapping("/{idUser}")
  public ResponseEntity<?> createAccount(@PathVariable Long idUser, @RequestBody AccountDto dto){
      try {
          return ResponseEntity.status(HttpStatus.CREATED).body(service.createAccount(idUser, dto));
      } catch (Exception e) {
          responseError.setError("BAD_REQUEST");
          responseError.setMessege(e.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
      }
  }

  

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        try {
            String deletionResult = service.deleteAccount(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletionResult);
        } catch (Exception ex) {
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
        }
    }


}
