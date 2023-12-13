package com.SpringBanking.api.controllers;

import com.SpringBanking.api.models.dto.TransferDto;
import com.SpringBanking.api.services.TransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    TransferService transferService;

    public TransferController(TransferService service) {
        this.transferService = service;
    }

    @GetMapping
    public ResponseEntity<?> getTransfers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transferService.getTransfers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTransfer(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transferService.getTransferById(id));
    }

    @PostMapping
    public ResponseEntity<?> createTransfer(@RequestBody TransferDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.createTransfer(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateTransfer(@PathVariable Long id, @RequestBody TransferDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.updateTransfer(id, dto));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteTransfer(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.deleteTransfer(id));
    }
}
