package com.stc.systemdesign.exception;

public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String exception){
        super(exception);
    }
}
