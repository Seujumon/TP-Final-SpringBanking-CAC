package com.SpringBanking.api.dto;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Integer dni;
    private Date birthdate;
    private String homeaddres;
    private List<?> accounts;

    public UserDto(UserDto.Builder builder){
        id=builder.id;
        username=builder.username;
        email=builder.email;
        password=builder.password;
        dni=builder.dni;
        birthdate=builder.birthdate;
        homeaddres=builder.homeaddres;
        accounts=builder.accounts;
    }

    public static class Builder{

        private Long id;
        private String username;
        private String email;
        private String password;
        private Integer dni;
        private Date birthdate;
        private String homeaddres;

        private List<?> accounts;

        public UserDto.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDto.Builder username(String username) {
            this.username = username;
            return this;
        }

        public UserDto.Builder email(String email) {
            this.email = email;
            return this;
        }

        public UserDto.Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserDto.Builder dni(Integer dni) {
            this.dni = dni;
            return this;
        }

        public UserDto.Builder birthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public UserDto.Builder homeaddres(String homeaddres) {
            this.homeaddres = homeaddres;
            return this;
        }

        public UserDto.Builder accounts(List<?> accounts) {
            this.accounts = accounts;
            return this;
        }

        public UserDto build() {
            return new UserDto(this);
        }

    }

}
