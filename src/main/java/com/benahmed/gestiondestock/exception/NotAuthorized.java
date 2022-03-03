package com.benahmed.gestiondestock.exception;

import lombok.Getter;

public class NotAuthorized extends RuntimeException {
    @Getter
    private ErrorCodes errorCode;
    public NotAuthorized(String msg){
        super(msg);
    }
    public NotAuthorized(String msg, Throwable cause){
        super(msg, cause);
    }
    public NotAuthorized(String msg,Throwable cause, ErrorCodes errorCode){
        super(msg,cause);
        this.errorCode = errorCode;
    }
    public NotAuthorized(String msg, ErrorCodes errorCode){
        super(msg);
        this.errorCode= errorCode;
    }
}
