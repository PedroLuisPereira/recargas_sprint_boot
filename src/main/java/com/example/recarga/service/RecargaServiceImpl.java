package com.example.recarga.service;

import com.example.recarga.models.Recarga;
import com.example.recarga.repository.RecargaRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecargaServiceImpl implements RecargaService {

    @Autowired
    RecargaRepository recargaRepository;

    @Override
    public Recarga save(Recarga recarga) {
        if (recarga == null) {
            return new Recarga();
        }
        return recargaRepository.save(recarga);
    }

    @Override
    public List<Recarga> findAll() {
        return recargaRepository.findAll();
    }
}
