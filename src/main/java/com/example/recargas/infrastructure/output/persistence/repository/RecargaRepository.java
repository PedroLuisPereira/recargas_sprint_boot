package com.example.recargas.infrastructure.output.persistence.repository;

import com.example.recargas.infrastructure.output.persistence.entity.PersonaEntity;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RecargaRepository extends JpaRepository<RecargaEntity, Long> {

    List<RecargaEntity> findByPersonaEntity(PersonaEntity personaEntity);
}


