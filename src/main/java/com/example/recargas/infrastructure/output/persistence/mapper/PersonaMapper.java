package com.example.recargas.infrastructure.adapters.output.persistence.mapper;

import com.example.recargas.domain.model.Persona;
import com.example.recargas.infrastructure.adapters.output.persistence.entity.PersonaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonaMapper {

    @Autowired
    private ModelMapper mapper;

    public Persona toPersona(PersonaEntity entity) {
        return Persona.getInstance(entity.getId(), entity.getNombre(), entity.getEmail());
    }

    public PersonaEntity toEntity(Persona product) {
        return mapper.map(product, PersonaEntity.class);
    }

}
