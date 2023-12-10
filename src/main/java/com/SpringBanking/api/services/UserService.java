package com.SpringBanking.api.services;

import java.util.List;

import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.UserDto;

public interface UserService {
    public UserDto save(UserDto userDto);
    public UserDto findById(Long id);
    public List<UserDto> findAll(); 
    public void deleteById(Long id);
    public UserDto updateUser(Long id, UserDto userDto);
}
