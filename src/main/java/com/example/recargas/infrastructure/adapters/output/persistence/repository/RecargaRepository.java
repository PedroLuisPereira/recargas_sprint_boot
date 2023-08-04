package com.example.recargas.infrastructure.adapters.output.persistence.repository;

import com.example.recargas.infrastructure.adapters.output.persistence.entity.RecargaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecargaRepository extends JpaRepository<RecargaEntity, Long> {

}


