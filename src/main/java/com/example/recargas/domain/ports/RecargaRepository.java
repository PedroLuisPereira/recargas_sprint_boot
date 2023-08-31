package com.example.recargas.domain.ports;


import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;

import java.util.List;
import java.util.Optional;

public interface RecargaRepository {

    List<Recarga> list();

    Optional<Recarga> listarByid(long id);

    List<Recarga> listarByPersona(Persona persona);

    Recarga save(Recarga recarga);


}
