package com.example.recargas.domain.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CompraDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Operador;
    private double valor;
    public CompraDto(String operador, double valor) {
        Operador = operador;
        this.valor = valor;
    }

}