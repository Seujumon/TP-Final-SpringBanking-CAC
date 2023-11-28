package com.SpringBanking.api.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.SpringBanking.api.mappers.AccountMapper;
import com.SpringBanking.api.mappers.UserMapper;
import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.AccountDto;
import com.SpringBanking.api.models.dto.UserDto;
import com.SpringBanking.api.repositories.UserRepository;
import com.SpringBanking.api.services.AccountService;
import com.SpringBanking.api.services.UserService;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepo;
    private final AccountService accountService;

    public UserServiceImp(UserRepository _userRepo, AccountService _accountService) {
        userRepo = _userRepo;
        accountService=_accountService; 
    }

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepo.save(UserMapper.dtoToUser(userDto));
        if(userDto.getAccounts() == null){
            accountService.createDefaultAccount(user.getId());
        }
        return UserMapper.userToDto(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll()
            .stream()
            .map(UserMapper::userToDto)
            .collect(Collectors.toList());
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            userRepo.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        if (userRepo.findById(id).isEmpty())
            return null;
        else {
            User user = userRepo.findById(id).get();
            
            if (userDto.getUsername() != null)
                user.setUsername(userDto.getUsername());
            if (userDto.getEmail() != null)
                user.setEmail(userDto.getEmail());
            if (userDto.getPassword() != null)
                user.setPassword(userDto.getPassword());
            if (userDto.getDni() != null)
                user.setDni(userDto.getDni());
            if (userDto.getBirthdate() != null)
                user.setBirthdate(userDto.getBirthdate());
            if (userDto.getHomeaddres() != null)
                user.setHomeaddres(userDto.getHomeaddres());
            if(userDto.getAccounts() != null){
                AccountDto accountDto = AccountMapper.accountToDto(userDto.getAccounts().get(0));
                accountService.updateAccount(accountDto.getId(), accountDto);
            }

            userRepo.save(user);
            return UserMapper.userToDto(user);
        }
    }

}
