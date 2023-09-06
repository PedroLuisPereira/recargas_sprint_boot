package com.example.recargas.application.persona.consulta;

import com.example.recargas.application.persona.PersonaTransformador;
import com.example.recargas.application.persona.dto.PersonaRespuestaDto;
import com.example.recargas.domain.service.PersonaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonaListarById {

    private final PersonaService personaService;

    public PersonaListarById(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Transactional
    public PersonaRespuestaDto ejecutar(long id) {
        return PersonaTransformador.transformar(personaService.listarById(id));
    }


}