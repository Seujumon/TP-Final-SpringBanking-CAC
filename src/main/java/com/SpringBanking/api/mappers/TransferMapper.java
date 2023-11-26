package com.SpringBanking.api.mappers;

import com.SpringBanking.api.models.Transfer;
import com.SpringBanking.api.models.dto.TransferDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TransferMapper {

    public TransferDto transferToDto(Transfer transfer){
        TransferDto transferDto = new TransferDto();
        transferDto.setId(transfer.getId());
        transferDto.setOriginAccount(transfer.getOriginAccount());
        transferDto.setDestinationAccount(transfer.getDestinationAccount());
        transferDto.setAmount(transfer.getAmount());
        transferDto.setDateTime(transfer.getDateTime());
        return transferDto;
    }

    public Transfer dtoToTransfer(TransferDto transferDto){
        Transfer transfer = new Transfer();
        transfer.setOriginAccount(transferDto.getOriginAccount());
        transfer.setDestinationAccount(transferDto.getDestinationAccount());
        transfer.setAmount(transferDto.getAmount());
        transfer.setDateTime(transferDto.getDateTime());
        return transfer;
    }
}
