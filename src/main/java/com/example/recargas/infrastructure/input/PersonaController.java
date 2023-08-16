package com.example.recargas.infrastructure.input;

import java.util.List;

import com.example.recargas.application.persona.comando.PersonaActualizar;
import com.example.recargas.application.persona.comando.PersonaCrear;
import com.example.recargas.application.persona.comando.PersonaEliminar;
import com.example.recargas.application.persona.consulta.PersonaListar;
import com.example.recargas.application.persona.consulta.PersonaListarById;
import com.example.recargas.application.persona.dto.PersonaCrearDto;
import com.example.recargas.application.persona.dto.PersonaRespuestaDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
@CrossOrigin(origins = "*")
public class PersonaController {

    private final PersonaCrear personaCrear;
    private final PersonaActualizar personaActualizar;
    private final PersonaListar personaListar;
    private final PersonaListarById personaListarById;
    private final PersonaEliminar personaEliminar;

    public PersonaController(PersonaCrear personaCrear,
            PersonaActualizar personaActualizar,
            PersonaListar personaListar,
            PersonaListarById personaListarById,
            PersonaEliminar personaEliminar) {
        this.personaCrear = personaCrear;
        this.personaActualizar = personaActualizar;
        this.personaListar = personaListar;
        this.personaListarById = personaListarById;
        this.personaEliminar = personaEliminar;
    }

    @GetMapping("/personas")
    public List<PersonaRespuestaDto> list() {
        return personaListar.ejecutar();
    }

    @GetMapping("/personas/{id}")
    public PersonaRespuestaDto listByid(@PathVariable(value = "id") Long id) {
        return personaListarById.ejecutar(id);
    }

    @PostMapping("/personas")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonaRespuestaDto createPersona(@RequestBody PersonaCrearDto personaCrearDto) {
        return personaCrear.ejecutar(personaCrearDto);
    }

    @PutMapping("/personas/{id}")
    public PersonaRespuestaDto updatePersona(@PathVariable(value = "id") Long id, @RequestBody PersonaCrearDto personaCrearDto) {
        return personaActualizar.ejecutar(id, personaCrearDto);
    }

    @DeleteMapping("/personas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        personaEliminar.ejecutar(id);
    }

}
