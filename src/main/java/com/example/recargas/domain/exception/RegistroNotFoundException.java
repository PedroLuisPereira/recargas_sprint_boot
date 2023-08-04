package com.example.recargas.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNotFoundException extends RuntimeException{
    
    public RegistroNotFoundException(String msg){
        super(msg);
    }
}
