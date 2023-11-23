package com.SpringBanking.api.models;

import com.SpringBanking.api.models.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String cbu;
    private String alias;
    private BigDecimal amount;
    //TODO Relación con la clase USER
    /*
    @ManyToOne
    private User owner;

     */
    //TODO Relación con la clase TRANSFER
    /*
    @OneToMany
    private List<Transfer> transfers;
    * */
}
