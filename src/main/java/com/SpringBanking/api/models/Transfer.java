package com.SpringBanking.api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transfers")
@Getter
@Setter
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long originAccount;
    private Long destinationAccount;
    private BigDecimal amount;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

}
