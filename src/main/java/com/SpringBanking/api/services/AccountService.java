package com.SpringBanking.api.services;

import java.util.List;

import com.SpringBanking.api.models.dto.AccountDto;

public interface AccountService {
    public List<AccountDto>getAccounts();
    public AccountDto getAccountById(Long id);
    public AccountDto createAccount(Long id, AccountDto dto) throws Exception;
    public AccountDto createDefaultAccount(Long id);
    public AccountDto updateAccount(Long id, AccountDto dto);
    public String deleteAccount(Long id);
}
