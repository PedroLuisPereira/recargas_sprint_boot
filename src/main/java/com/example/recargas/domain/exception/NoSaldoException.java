package com.example.recargas.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoSaldoException extends RuntimeException{

    public NoSaldoException(String msg){
        super(msg);
    }
}
