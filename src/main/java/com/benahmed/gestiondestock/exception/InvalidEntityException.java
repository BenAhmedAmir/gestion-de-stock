package com.benahmed.gestiondestock.exception;

import lombok.Getter;

import java.util.List;

// lors de valide d'une entité
public class InvalidEntityException extends RuntimeException {
    @Getter
    private ErrorCodes errorCode;
    @Getter
    private List<String> errors;
    public InvalidEntityException(String msg){
        super(msg);
    }
    public InvalidEntityException(String msg, Throwable cause){
        super(msg, cause);
    }
    public InvalidEntityException(String msg,Throwable cause, ErrorCodes errorCode){
        super(msg,cause);
        this.errorCode = errorCode;
    }
    public InvalidEntityException(String msg, ErrorCodes errorCode){
        super(msg);
        this.errorCode= errorCode;
    }
    public InvalidEntityException(String msg, ErrorCodes errorCode, List<String> errors){
        super(msg);
        this.errorCode= errorCode;
        this.errors=errors;
    }
}
