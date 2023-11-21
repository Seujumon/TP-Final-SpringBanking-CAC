package com.SpringBanking.api.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private Integer dni;
    private Date birthdate;
    private String homeaddres;

    private List<?> accounts;

    public User(String username, String email, String password, Integer dni, Date birthdate, String homeaddres,
        List<?> accounts) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.birthdate = birthdate;
        this.homeaddres = homeaddres;
        this.accounts = accounts;
    }
}
