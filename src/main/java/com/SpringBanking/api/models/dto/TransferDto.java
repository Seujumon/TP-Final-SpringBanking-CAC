package com.SpringBanking.api.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class TransferDto {
    private Long id;
    private Long originAccount;
    private Long destinationAccount;
    private BigDecimal amount;
    private LocalDateTime dateTime;
}
