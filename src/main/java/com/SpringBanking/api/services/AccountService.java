package com.SpringBanking.api.services;

import com.SpringBanking.api.repositories.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository){
        this.repository = repository;
        
    }
}
