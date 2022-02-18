package com.benahmed.gestiondestock.exception;

import lombok.Getter;

// lorsque on cherche dans la BD
public class EntityNotFoundException extends RuntimeException{
    @Getter
    private ErrorCodes errorCode;
    public EntityNotFoundException(String msg){
        super(msg);
    }
    public EntityNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
    public EntityNotFoundException(String msg,Throwable cause, ErrorCodes errorCode){
        super(msg,cause);
        this.errorCode = errorCode;
    }
    public EntityNotFoundException(String msg, ErrorCodes errorCode){
        super(msg);
        this.errorCode= errorCode;
    }
}
