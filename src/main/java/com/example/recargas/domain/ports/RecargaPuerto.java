package com.example.recargas.domain.ports;


import com.example.recargas.domain.model.Recarga;

import java.util.List;
import java.util.Optional;

public interface RecargaPuerto {

    Optional<Recarga> listarByid(long id);

    Recarga save(Recarga recarga);
    List<Recarga> list();

}
