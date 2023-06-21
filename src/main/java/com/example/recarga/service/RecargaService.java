package com.example.recarga.service;

import java.util.List;

import com.example.recarga.models.Recarga;

public interface RecargaService {

    public List<Recarga> findAll();

    public Recarga save(Recarga recarga);

}
