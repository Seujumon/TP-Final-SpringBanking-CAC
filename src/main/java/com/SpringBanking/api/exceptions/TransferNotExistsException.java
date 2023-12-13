package com.SpringBanking.api.exceptions;

import com.SpringBanking.api.exceptions.enums.ClassNotExist;

public class TransferNotExistsException extends RuntimeException {
    public TransferNotExistsException(ClassNotExist message) {
        super(message.getMessage());
    }
}
