package com.example.aop.exception;

public class SqlException extends RuntimeException{
    public SqlException(){
        super("");
    }

    @Override
    public String getMessage(){
        return "SqlException";
    }
}
