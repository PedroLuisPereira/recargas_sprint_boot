package com.example.recargas.application.persona.comando;

import com.example.recargas.domain.service.PersonaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class PersonaEliminar {

    private final PersonaService personaService;

    public PersonaEliminar(PersonaService personaService) {
        this.personaService = personaService;
    }

    @Transactional
    public void ejecutar(long id) {
        personaService.eliminar(id);
    }
}
