package com.SpringBanking.api.services;

import java.util.List;
import java.util.Optional;

import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.UserDto;

public interface UserService {
    public User save(UserDto userDto);
    public Optional<User> findById(Long id);
    public List<UserDto> findAll(); 
    public boolean deleteById(Long id);
    public UserDto updateUser(Long id, UserDto userDto);
}
