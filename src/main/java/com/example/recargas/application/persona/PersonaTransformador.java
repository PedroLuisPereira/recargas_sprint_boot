package com.example.recargas.application.persona;

import com.example.recargas.application.persona.dto.PersonaCrearDto;
import com.example.recargas.application.persona.dto.PersonaRespuestaDto;
import com.example.recargas.domain.dto.PersonaSolicitudActualizar;
import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.model.Persona;

public class PersonaTransformador {

    private PersonaTransformador() {
    }

    public static PersonaSolicitudCrear transformar(PersonaCrearDto personaCrearDto) {
        return new PersonaSolicitudCrear(
          personaCrearDto.getNombre(),
          personaCrearDto.getEmail()
        );
    }

    public static PersonaSolicitudActualizar transformar(long id, PersonaCrearDto personaCrearDto) {
        return new PersonaSolicitudActualizar(
          id,
          personaCrearDto.getNombre(),
          personaCrearDto.getEmail()
        );
    }

    public static PersonaRespuestaDto transformar(Persona persona) {
        return new PersonaRespuestaDto(
          persona.getId(),
          persona.getNombre(),
          persona.getEmail()
        );
    }
}
