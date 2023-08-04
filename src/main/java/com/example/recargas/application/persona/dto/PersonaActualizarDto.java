package com.example.recargas.application.persona.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaActualizarDto {

    private long id;
    private String nombre;
    private String email;

}
