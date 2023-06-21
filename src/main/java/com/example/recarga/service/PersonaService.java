package com.example.recarga.service;

import java.util.List;

import com.example.recarga.models.Persona;

public interface PersonaService {

    public Persona findById(Long persona_id);
    
    public List<Persona> findAll();

    public Persona save(Persona persona);

}
