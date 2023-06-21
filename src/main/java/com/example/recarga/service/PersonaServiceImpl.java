package com.example.recarga.service;

import com.example.recarga.models.Persona;
import com.example.recarga.repository.PersonaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public Persona findById(Long persona_id) {
        return personaRepository.findById(persona_id).orElse(null);
    }

    @Override
    public Persona save(Persona persona) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public List<Persona> findAll() {
        return personaRepository.findAll();
    }

}
