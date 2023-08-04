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
          Persona.getInstance(
            entity.getPersonaEntity().getId(),
            entity.getPersonaEntity().getNombre(),
            entity.getPersonaEntity().getEmail()
          )
        );
    }

    public RecargaEntity toEntity(Recarga recarga) {

        return new RecargaEntity(
          recarga.getId(),
          recarga.getValor(),
          recarga.getCelular(),
          recarga.getOperador(),
          mapper.map(recarga.getPersona(), PersonaEntity.class)
        );
    }

}
