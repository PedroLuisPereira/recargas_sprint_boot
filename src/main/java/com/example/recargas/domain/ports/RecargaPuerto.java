package com.example.recargas.domain.ports;


import com.example.recargas.domain.model.Recarga;

import java.util.List;

public interface RecargaPuerto {

    Recarga save(Recarga recarga);
    List<Recarga> list();

}
