package com.SpringBanking.api.models;

import com.SpringBanking.api.models.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Column(unique = true, nullable = false)
    private String cbu;
    @Column(unique = true)
    private String alias;
    private BigDecimal amount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

     
    //TODO Relación con la clase TRANSFER
    /*
    @OneToMany
    private List<Transfer> transfers;
    * */
}
