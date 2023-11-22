package com.SpringBanking.api.models;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private Integer dni;
    private Date birthdate;
    private String homeaddres;

    // private List<Account> accounts;

    public User(String username, String email, String password, Integer dni, Date birthdate, String homeaddres
           /*, List<Account> accounts */ ) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.birthdate = birthdate;
        this.homeaddres = homeaddres;
        // this.accounts = accounts;
    }

    public User(User.Builder builder) {
        id = builder.id;
        username = builder.username;
        email = builder.email;
        password = builder.password;
        dni = builder.dni;
        birthdate = builder.birthdate;
        homeaddres = builder.homeaddres;
        // accounts = builder.accounts;
    }

    public static class Builder {

        private Long id;
        private String username;
        private String email;
        private String password;
        private Integer dni;
        private Date birthdate;
        private String homeaddres;

        // private List<Account> accounts;

        public User.Builder id(Long id) {
            this.id = id;
            return this;
        }

        public User.Builder username(String username) {
            this.username = username;
            return this;
        }

        public User.Builder email(String email) {
            this.email = email;
            return this;
        }

        public User.Builder password(String password) {
            this.password = password;
            return this;
        }

        public User.Builder dni(Integer dni) {
            this.dni = dni;
            return this;
        }

        public User.Builder birthdate(Date birthdate) {
            this.birthdate = birthdate;
            return this;
        }

        public User.Builder homeaddres(String homeaddres) {
            this.homeaddres = homeaddres;
            return this;
        }

        // public User.Builder accounts(List<Account> accounts) {
        //     this.accounts = accounts;
        //     return this;
        // }

        public User build() {
            return new User(this);
        }

    }

}
