package com.example.recargas.application.recarga.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecargaSaldoDto {

    private Long id;

    private double valor;

    private String operador;


}