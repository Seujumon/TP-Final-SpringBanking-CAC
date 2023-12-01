package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.TransferDto;
import com.SpringBanking.api.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
    public ResponseEntity<TransferDto> createTransfer(@RequestBody TransferDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TransferDto> updateTransfer(@PathVariable Long id, @RequestBody TransferDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(transferService.updateTransfer(id, dto));
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(transferService.deleteTransfer(id));
    }

}
