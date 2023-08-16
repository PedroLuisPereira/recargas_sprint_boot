package com.example.recargas.application.persona.comando;

import com.example.recargas.application.persona.PersonaTransformador;
import com.example.recargas.application.persona.dto.PersonaCrearDto;
import com.example.recargas.application.persona.dto.PersonaRespuestaDto;
import com.example.recargas.domain.dto.PersonaSolicitudActualizar;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.service.PersonaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonaActualizar {

    private final PersonaService personaService;

    public PersonaActualizar(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Transactional
    public PersonaRespuestaDto ejecutar(long id, PersonaCrearDto personaCrearDto) {
        PersonaSolicitudActualizar personaSolicitudActualizar = PersonaTransformador.trasnformar(id, personaCrearDto);
        Persona persona = personaService.actualizar(personaSolicitudActualizar);
        return PersonaTransformador.trasnformar(persona);

    }
}
