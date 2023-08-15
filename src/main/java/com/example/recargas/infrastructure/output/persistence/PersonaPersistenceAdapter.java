package com.example.recargas.infrastructure.output.persistence;

import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.ports.PersonaPuerto;
import com.example.recargas.infrastructure.output.persistence.entity.PersonaEntity;
import com.example.recargas.infrastructure.output.persistence.mapper.PersonaMapper;
import com.example.recargas.infrastructure.output.persistence.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PersonaPersistenceAdapter implements PersonaPuerto {

    private final PersonaRepository productRepository;

    private final PersonaMapper productMapper;

    @Override
    public List<Persona> listar() {
        List<PersonaEntity> personas = productRepository.findAll();
        return personas.stream()
          .map(productMapper::toPersona)
          .collect(Collectors.toList()
          );
    }

    @Override
    public List<Persona> listarByEmail(String email) {
        return productRepository.findByEmail(email)
          .stream()
          .map(productMapper::toPersona)
          .collect(Collectors.toList());
    }

    @Override
    public Optional<Persona> listarByid(long id) {
        Optional<PersonaEntity> personaEntity = productRepository.findById(id);
        //return personaEntity.isEmpty() ? Optional.empty() : Optional.of(productMapper.toPersona(personaEntity.get()));
        return personaEntity.map(productMapper::toPersona);
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity productEntity = productMapper.toEntity(persona);
        productEntity = productRepository.save(productEntity);
        return productMapper.toPersona(productEntity);
    }

    @Override
    public void eliminar(long id) {
        productRepository.deleteById(id);

    }
}
