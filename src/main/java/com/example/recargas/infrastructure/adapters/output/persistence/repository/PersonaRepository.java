package com.example.recargas.infrastructure.adapters.output.persistence.repository;

import com.example.recargas.infrastructure.adapters.output.persistence.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {

    List<PersonaEntity> findByEmail(String email);
}
