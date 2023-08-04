package com.example.recargas.infrastructure.adapters.output.persistence.mapper;

import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.infrastructure.adapters.output.persistence.entity.PersonaEntity;
import com.example.recargas.infrastructure.adapters.output.persistence.entity.RecargaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RecargaMapper {

    @Autowired
    private ModelMapper mapper;

    public Recarga toRecarga(RecargaEntity entity) {

        return Recarga.getInstance(
          entity.getId(),
          entity.getValor(),
          entity.getCelular(),
          entity.getOperador(),
          entity.getPersonaEntity().getId());

    }

    public RecargaEntity toEntity(Persona persona, Recarga recarga) {

        PersonaEntity personaEntity = mapper.map(persona, PersonaEntity.class);

        return new RecargaEntity(
          recarga.getId(),
          recarga.getValor(),
          recarga.getCelular(),
          recarga.getOperador(),
          personaEntity
        );
    }

}
