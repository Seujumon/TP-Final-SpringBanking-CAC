package com.SpringBanking.api.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String s) {
        super(s);
    }
}
