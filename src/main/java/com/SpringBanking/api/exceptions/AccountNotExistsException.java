package com.SpringBanking.api.exceptions;

import com.SpringBanking.api.exceptions.enums.ClassNotExist;

public class AccountNotExistsException extends RuntimeException {
    public AccountNotExistsException(ClassNotExist message){
        super(message.getMessage());
    }
}
