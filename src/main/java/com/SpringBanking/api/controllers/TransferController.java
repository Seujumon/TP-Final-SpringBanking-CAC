package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.TransferDto;
import com.SpringBanking.api.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    TransferService transferService;
    public TransferController(TransferService service){
        this.transferService= service;
    }
    @GetMapping
    public ResponseEntity<?> getTransfers(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transferService.getTransfers());}
        catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTransfer(@PathVariable Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transferService.getTransferById(id));
        }catch(Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró una transferencia con el id: " + id);
        }
    }


   @PostMapping
    public ResponseEntity<?> createTransfer(@RequestBody TransferDto dto){
        try {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(dto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTransfer(@PathVariable Long id, @RequestBody TransferDto dto){
        try {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.updateTransfer(id, dto));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }    
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable Long id){
        try {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.deleteTransfer(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
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
