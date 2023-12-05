package com.SpringBanking.api.models.dto;

import com.SpringBanking.api.models.User;
import com.SpringBanking.api.models.enums.AccountType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private AccountType type;
    private String cbu;
    private String alias;
    private BigDecimal amount;
    private User owner;

    //TODO Relación con la clase TRANSFERDTO
    /*
    @OneToMany
    private List<Transfer> transfers;
    * */
}
