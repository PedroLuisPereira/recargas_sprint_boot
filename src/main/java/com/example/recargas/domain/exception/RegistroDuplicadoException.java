package com.example.recargas.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RegistroDuplicadoException extends RuntimeException{

    public RegistroDuplicadoException(String msg){
        super(msg);
    }
}
