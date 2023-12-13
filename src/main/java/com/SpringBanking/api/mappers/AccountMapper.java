package com.SpringBanking.api.mappers;

import com.SpringBanking.api.models.Account;
import com.SpringBanking.api.models.dto.AccountDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {

    public AccountDto accountToDto(Account account){
        return AccountDto.builder()
            .alias(account.getAlias())
            .cbu(account.getCbu())
            .type(account.getType())
            .amount(account.getAmount())
            .id(account.getId())
            .owner(account.getOwner())
            .transfers(account.getTransfers())
            .build();
    }
    public Account dtoToAccount(AccountDto dto){
        return Account.builder()
            .alias(dto.getAlias())
            .cbu(dto.getCbu())
            .type(dto.getType())
            .amount(dto.getAmount())
            .owner(dto.getOwner())
            .transfers(dto.getTransfers())
            .build();
    }
}