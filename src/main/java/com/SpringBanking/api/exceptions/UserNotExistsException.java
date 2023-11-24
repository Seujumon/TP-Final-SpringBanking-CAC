package com.SpringBanking.api.exceptions;

public class UserNotExistsException extends RuntimeException {
    public UserNotExistsException(String s) {
        super(s);
    }
}
