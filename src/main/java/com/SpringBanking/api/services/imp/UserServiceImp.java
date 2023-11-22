package com.SpringBanking.api.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.SpringBanking.api.mapper.UserMapper;
import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.UserDto;
import com.SpringBanking.api.repositories.UserRepository;
import com.SpringBanking.api.services.UserService;

@Service
public class UserServiceImp implements UserService {
    
    private final UserRepository userRepo;

    public UserServiceImp(UserRepository _userRepo){
        userRepo=_userRepo;
    }

    @Override
    public User save(UserDto userDto){
        User user = userRepo.save(UserMapper.dtoToUser(userDto));
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public List<User> findAll() {
       return userRepo.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            userRepo.deleteById(id);
            return true;
        }
        else
            return false;
    }
}
