package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.ResponseError;
import com.SpringBanking.api.models.dto.TransferDto;
import com.SpringBanking.api.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    TransferService transferService;
    ResponseError responseError;
    public TransferController(TransferService service, ResponseError responseError){
        this.transferService= service;
        this.responseError=responseError;
    }
    @GetMapping
    public ResponseEntity<?> getTransfers(){
        try{
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transferService.getTransfers());}
        catch (Exception e){
            responseError.setError("INTERNAL_SERVER_ERROR");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTransfer(@PathVariable Long id){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(transferService.getTransferById(id));
        }catch(Exception e){
            responseError.setError("NOT_FOUND");
            responseError.setMessege(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(responseError);
        }
    }


   @PostMapping("/{idAccount}")
    public ResponseEntity<?> createTransfer(@PathVariable Long idAccount,@RequestBody TransferDto dto){
        try {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(dto, idAccount));
        } catch (Exception e) {
            responseError.setError("Bad_REQUEST");
            responseError.setMessege(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
        }
        }
    

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTransfer(@PathVariable Long id, @RequestBody TransferDto dto){
        try {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.updateTransfer(id, dto));
        } catch (Exception e){
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
        }    
    }
    
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTransfer(@PathVariable Long id){
        try {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.deleteTransfer(id));
        } catch (Exception e) {
            responseError.setError("BAD_REQUEST");
            responseError.setMessege(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
        }
    }
}
