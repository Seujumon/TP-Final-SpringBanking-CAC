package com.SpringBanking.api.services;

import com.SpringBanking.api.exceptions.UserNotExistsException;
import com.SpringBanking.api.mappers.AccountMapper;
import com.SpringBanking.api.models.Account;
import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.repositories.AccountRepository;
import com.SpringBanking.api.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository repository;
    private final UserRepository userRepo;

    public AccountService(AccountRepository repository, UserRepository userRepo) {
        this.repository = repository;
        this.userRepo = userRepo;
    }

    public List<AccountDto> getAccounts() {
        List<Account> accounts = repository.findAll();
        return accounts.stream()
                .map(AccountMapper::accountToDto)
                .collect(Collectors.toList());
    }

    public AccountDto getAccountById(Long id) {
        Account entity = repository.findById(id).get();
        return AccountMapper.accountToDto(entity);
    }

    public AccountDto createAccount(AccountDto dto) {
        //dto.setType(AccountType.SAVINGS_BANK);
        dto.setType(dto.getType());
        dto.setAmount(BigDecimal.ZERO);
        Account newAccount = repository.save(AccountMapper.dtoToAccount(dto));
        return AccountMapper.accountToDto(newAccount);
    }

    public AccountDto updateAccount(Long id, AccountDto dto) {
        if (repository.existsById(id)) {
            Account accountToModify = repository.findById(id).get();
            if (dto.getAlias() != null) {
                accountToModify.setAlias(dto.getAlias());
            }

            if (dto.getType() != null) {
                accountToModify.setType(dto.getType());
            }

            if (dto.getCbu() != null) {
                accountToModify.setCbu(dto.getCbu());
            }

            if (dto.getAmount() != null) {
                accountToModify.setAmount(dto.getAmount());
            }

            if (dto.getOwner() != null) {
                accountToModify.setOwner(dto.getOwner());
            }

            Account accountModified = repository.save(accountToModify);

            return AccountMapper.accountToDto(accountModified);
        }
        return null;
    }

    public String deleteAccount(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "La cuenta con id: " + id + " ha sido eliminada";
        } else {
            throw new UserNotExistsException("La cuenta a eliminar no existe");
        }
    }
}
