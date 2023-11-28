package com.SpringBanking.api.mappers;

import com.SpringBanking.api.models.Account;
import com.SpringBanking.api.models.dto.AccountDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountMapper {
    public AccountDto accountToDto(Account account){
        AccountDto dto = new AccountDto();
        dto.setAlias(account.getAlias());
        dto.setCbu(account.getCbu());
        dto.setType(account.getType());
        dto.setAmount(account.getAmount());
        dto.setId(account.getId());
        dto.setOwner(account.getOwner());;
        return dto;
    }

    public Account dtoToAccount(AccountDto dto){
        Account account = new Account();
        account.setAlias(dto.getAlias());
        account.setType(dto.getType());
        account.setCbu(dto.getCbu());
        account.setAmount(dto.getAmount());
        account.setOwner(dto.getOwner());
        return account;
    }
}
