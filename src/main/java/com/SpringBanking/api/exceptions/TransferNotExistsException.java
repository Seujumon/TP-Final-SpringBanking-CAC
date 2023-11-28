package com.SpringBanking.api.exceptions;

public class TransferNotExistsException extends RuntimeException {
    public TransferNotExistsException(String s) {
        super(s);
    }
}
