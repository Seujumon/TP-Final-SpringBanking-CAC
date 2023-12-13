package com.SpringBanking.api.services;

import java.util.List;

import com.SpringBanking.api.models.dto.TransferDto;

public interface TransferService {
    public List<TransferDto> getTransfers();
    public TransferDto getTransferById(Long id);
    public TransferDto createTransfer(TransferDto dto);
    public TransferDto updateTransfer(Long id, TransferDto dto);
    public String deleteTransfer(Long id);
}
