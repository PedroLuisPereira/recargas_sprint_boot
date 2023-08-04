package com.example.recargas.application.recarga.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecargaCrearDto {

    private double valor;

    private String celular;

    private String operador;

    private long personaId;
}
