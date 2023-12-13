package com.SpringBanking.api.exceptions;

import com.SpringBanking.api.exceptions.enums.ClassNotExist;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(ClassNotExist message) {
        super(message.getMessage());
    }
}
