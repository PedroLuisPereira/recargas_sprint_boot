package com.example.recargas.infrastructure.output.persistence;

import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaPuerto;
import com.example.recargas.infrastructure.output.persistence.entity.PersonaEntity;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;
import com.example.recargas.infrastructure.output.persistence.mapper.PersonaMapper;
import com.example.recargas.infrastructure.output.persistence.mapper.RecargaMapper;
import com.example.recargas.infrastructure.output.persistence.repository.RecargaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RecargaPersistenceAdapter implements RecargaPuerto {

    private final RecargaRepository recargaRepository;

    private final RecargaMapper recargaMapper;
    private final PersonaMapper personaMapper;

    @Override
    public List<Recarga> list() {
        List<RecargaEntity> list = recargaRepository.findAll();
        return list.stream()
          .map(recargaMapper::toRecarga)
          .collect(Collectors.toList());
    }

    @Override
    public Optional<Recarga> listarByid(long id) {
        Optional<RecargaEntity> recargaEntity = recargaRepository.findById(id);
        return recargaEntity.map(recargaMapper::toRecarga);
    }

    @Override
    public List<Recarga> listarByPersona(Persona persona) {

        PersonaEntity personaEntity = personaMapper.toEntity(persona);

        List<RecargaEntity> list = recargaRepository.findByPersonaEntity(personaEntity);

        return list.stream()
          .map(recargaMapper::toRecarga)
          .collect(Collectors.toList());
    }

    @Override
    public Recarga save(Recarga recarga) {
        RecargaEntity recargaEntity = recargaMapper.toEntity(recarga);
        recargaEntity = recargaRepository.save(recargaEntity);
        return recargaMapper.toRecarga(recargaEntity);
    }

}
