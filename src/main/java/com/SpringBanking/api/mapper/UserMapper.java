package com.SpringBanking.api.mapper;

import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.dto.UserDto;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    
    public static User dtoToUser(UserDto userDto){
        return new User.Builder()
            .username(userDto.getUsername())
            .email(userDto.getEmail())
            .password(userDto.getPassword())
            .dni(userDto.getDni())
            .birthdate(userDto.getBirthdate())
            .homeaddres(userDto.getHomeaddres())
            // .accounts(userDto.getAccounts())
            .build();
    }

    public static UserDto userToDto(User user){
        return new UserDto.Builder()
            .id(user.getId())
            .username(user.getUsername())
            .email(user.getEmail())
            .password(user.getPassword())
            .dni(user.getDni())
            .birthdate(user.getBirthdate())
            .homeaddres(user.getHomeaddres())
            // .accounts(user.getAccounts())
            .build();
    }
}
