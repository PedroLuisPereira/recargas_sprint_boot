package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.PersonaSolicitudActualizar;
import com.example.recargas.domain.dto.PersonaSolicitudCrear;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.exception.RegistroDuplicadoException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.ports.PersonaPuerto;


import java.util.List;
import java.util.stream.Collectors;

public class PersonaService {

    private final PersonaPuerto personaRepositorio;

    public PersonaService(PersonaPuerto personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
    }

    public List<Persona> listar() {
        return personaRepositorio.listar();
    }

    public Persona listarById(long id) {
        return personaRepositorio.listarByid(id)
          .orElseThrow(() -> new RegistroNotFoundException("No se encontro persona con Id: " + id)
          );
    }

    public Persona crear(PersonaSolicitudCrear solicitudPersona) {

        Persona persona = Persona.getInstance(solicitudPersona.getNombre(), solicitudPersona.getEmail());

        List<Persona> list = personaRepositorio.listarByEmail(solicitudPersona.getEmail());

        if (list.isEmpty()) {
            persona = personaRepositorio.save(persona);
        } else {
            throw new RegistroDuplicadoException("Ya existe una persona con el email: " + solicitudPersona.getEmail() );
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
          .orElseThrow(() -> new RegistroNotFoundException("No se encontro la persona con Id: " + personaActualizar.getId())
          );

        List<Persona> list = personaRepositorio.listarByEmail(personaActualizar.getEmail())
          .stream()
          .filter(persona1 -> persona1.getId() == personaActualizar.getId())
          .collect(Collectors.toList());

        if (!list.isEmpty()) {
            persona = personaRepositorio.save(persona);
        } else {
            throw new RegistroDuplicadoException("Ya existe una persona con ese email: " + personaActualizar.getEmail() );
        }

        return personaRepositorio.save(persona);
    }

    public void eliminar(long id) {

        //validar si tiene recargas

        personaRepositorio.eliminar(id);
    }

}
