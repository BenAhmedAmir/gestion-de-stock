package com.benahmed.gestiondestock.exception;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException {
        @Getter
        private ErrorCodes errorCode;
    public InvalidOperationException(String msg){
            super(msg);
        }
    public InvalidOperationException(String msg, Throwable cause){
            super(msg, cause);
        }
    public InvalidOperationException(String msg,Throwable cause, ErrorCodes errorCode){
            super(msg,cause);
            this.errorCode = errorCode;
        }
    public InvalidOperationException(String msg, ErrorCodes errorCode){
            super(msg);
            this.errorCode= errorCode;
        }
    }

