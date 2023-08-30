package com.example.recargas.application.persona.consulta;

import com.example.recargas.application.persona.PersonaTransformador;
import com.example.recargas.application.persona.dto.PersonaRespuestaDto;
import com.example.recargas.domain.service.PersonaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PersonaListar {

    private final PersonaService personaService;

    public PersonaListar(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Transactional(readOnly = true)
    public List<PersonaRespuestaDto> ejecutar() {

        return personaService.listar().stream()
          .map(PersonaTransformador::trasnformar)
          .collect(Collectors.toList()
          );
          
    }


}