package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.PersonaSolicitudActualizar;
import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.exception.RegistroDuplicadoException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.PersonaRepository;
import com.example.recargas.domain.ports.RecargaRepository;


import java.util.List;
import java.util.stream.Collectors;

public class PersonaService {

    private final PersonaRepository personaRepositorio;
    private final RecargaRepository recargaRepositorio;

    public PersonaService(PersonaRepository personaRepositorio, RecargaRepository recargaRepositorio) {
        this.personaRepositorio = personaRepositorio;
        this.recargaRepositorio = recargaRepositorio;
    }

    public List<Persona> listar() {
        return personaRepositorio.listar();
    }

    public Persona listarById(long id) {
        return personaRepositorio.listarByid(id)
          .orElseThrow(() -> new RegistroNotFoundException("No se encontro persona con ese Id")
          );
    }

    public Persona crear(PersonaSolicitudCrear solicitudPersona) {
        Persona persona = Persona.getInstance(solicitudPersona.getNombre(), solicitudPersona.getEmail());
        List<Persona> list = personaRepositorio.listarByEmail(solicitudPersona.getEmail());

        if (list.isEmpty()) {
            persona = personaRepositorio.save(persona);
        } else {
            throw new RegistroDuplicadoException("Ya existe una persona con ese email");
        }

        return persona;
    }

    public Persona actualizar(PersonaSolicitudActualizar personaActualizar) {

        Persona persona = Persona.getInstance(
          personaActualizar.getId(),
          personaActualizar.getNombre(),
          personaActualizar.getEmail()
        );

        personaRepositorio.listarByid(persona.getId())
          .orElseThrow(() -> new RegistroNotFoundException("No se encontro la persona ese Id")
          );

        List<Persona> list = personaRepositorio.listarByEmail(personaActualizar.getEmail())
          .stream()
          .filter(persona1 -> persona1.getId() != personaActualizar.getId())
          .collect(Collectors.toList());

        if (list.isEmpty()) {
            persona = personaRepositorio.save(persona);
        } else {
            throw new RegistroDuplicadoException("Ya existe una persona con ese email");
        }

        return personaRepositorio.save(persona);
    }

    public void eliminar(long id) {
        Persona persona = personaRepositorio.listarByid(id)
          .orElseThrow(() -> new RegistroNotFoundException("No se encontro persona con ese Id")
          );

        List<Recarga> list = recargaRepositorio.listarByPersona(persona);

        if (list.isEmpty()) {
            personaRepositorio.eliminar(id);
        } else {
            throw new RegistroDuplicadoException("No se puede eliminar registro");
        }

    }

}
