package com.example.recargas.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSaldoException extends RuntimeException{

    public NoSaldoException(String msg){
        super(msg);
    }
}
