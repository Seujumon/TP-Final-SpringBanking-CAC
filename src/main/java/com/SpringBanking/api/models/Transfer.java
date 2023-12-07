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
    @Column(nullable = false)
    private Long originAccount;
    @Column(nullable = false)
    private Long destinationAccount;
    private BigDecimal amount;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
