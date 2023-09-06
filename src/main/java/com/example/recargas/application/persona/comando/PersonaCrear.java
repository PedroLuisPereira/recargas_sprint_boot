package com.example.recargas.application.persona.comando;

import com.example.recargas.application.persona.PersonaTransformador;
import com.example.recargas.application.persona.dto.PersonaCrearDto;
import com.example.recargas.application.persona.dto.PersonaRespuestaDto;
import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.service.PersonaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonaCrear {

    private final PersonaService personaService;

    public PersonaCrear(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Transactional
    public PersonaRespuestaDto ejecutar(PersonaCrearDto personaCrearDto) {

        PersonaSolicitudCrear personaSolicitud = PersonaTransformador.transformar(personaCrearDto);
        Persona persona = personaService.crear(personaSolicitud);
        return PersonaTransformador.transformar(persona);

    }
}
