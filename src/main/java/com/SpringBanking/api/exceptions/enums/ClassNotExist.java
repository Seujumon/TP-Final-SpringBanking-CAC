package com.SpringBanking.api.exceptions.enums;

public enum ClassNotExist {
    USER_NOT_EXIST("USER NOT EXISTS"),
    ACCOUNT_NOT_EXIST("ACCOUNT NOT EXISTS"),
    TRANSFER_NOT_EXIST("TRANSFER NOT EXISTS");

    private final String message;

    ClassNotExist(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
