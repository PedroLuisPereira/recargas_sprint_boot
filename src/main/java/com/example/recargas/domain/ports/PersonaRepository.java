package com.example.recargas.domain.ports;

import com.example.recargas.domain.model.Persona;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {

    List<Persona> listar();

    List<Persona> listarByEmail(String email);

    Optional<Persona> listarByid(long id);

    Persona save(Persona persona);

    void eliminar(long id);


}
