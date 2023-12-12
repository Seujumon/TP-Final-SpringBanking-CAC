package com.SpringBanking.api.services;

import com.SpringBanking.api.exceptions.AccountNotExistsException;
import com.SpringBanking.api.exceptions.UserNotExistsException;
import com.SpringBanking.api.mappers.AccountMapper;
import com.SpringBanking.api.models.Account;
import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.models.enums.AccountType;
import com.SpringBanking.api.repositories.AccountRepository;
import com.SpringBanking.api.repositories.UserRepository;
import com.SpringBanking.api.utils.AccountGeneratorValue;

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
        Account entity = repository.findById(id).orElseThrow(()->{
            throw new AccountNotExistsException("Account con id:"+id+" inexistente");
        });
        return AccountMapper.accountToDto(entity);
    }

    public AccountDto createAccount(Long id, AccountDto dto) throws Exception{
        User user = userRepo.findById(id).orElseThrow(()->{
            throw new UserNotExistsException("User con id:" + id + "inexistente");
        });
        //Vreifica que el alias no sea igual a otras cuentas de usuario
        boolean aliasExist = user.getAccounts()
                                .stream()
                                .anyMatch( account -> account.getAlias().equals(dto.getAlias()));
        if (aliasExist) {
            throw new Exception("Alias ​​already existing in an account");
        }else{
            dto.setOwner(user);
            dto.setAmount(BigDecimal.ZERO);
            dto.setCbu(AccountGeneratorValue.generateCbu());
            Account newAccount = repository.save(AccountMapper.dtoToAccount(dto));
            return AccountMapper.accountToDto(newAccount);
        }

    }

    public AccountDto createDefaultAccount(Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> {
            throw new UserNotExistsException("User con id:" + id + "inexistente");
        });
        Account account = repository.save(Account
                .builder()
                .alias(AccountGeneratorValue.generateAlias(user.getUsername()))
                .cbu(AccountGeneratorValue.generateCbu())
                .type(AccountType.SAVINGS_BANK)
                .amount(BigDecimal.ZERO)
                .owner(user)
                .build());
        return AccountMapper.accountToDto(account);
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
            //Elimine la modificacion del cbu ya q no tendria que poder modificarse

            if (dto.getAmount() != null) {
                accountToModify.setAmount(dto.getAmount());
            }
            //Elimine la modificacion del usuario ya q no tendria que poder modificarse

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
            throw new AccountNotExistsException("La cuenta a eliminar no existe");
        }
    }
}
