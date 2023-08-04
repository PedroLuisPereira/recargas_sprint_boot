package com.example.recargas.application.recarga.consulta;

import com.example.recargas.application.recarga.RecargaTransformador;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;
import com.example.recargas.domain.service.RecargaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class RecargaListar {

    private final RecargaService recargaService;

    public RecargaListar(RecargaService recargaService) {
        this.recargaService = recargaService;
    }

    @Transactional
    public List<RecargaRespuestaDto> ejecutar() {

        return recargaService.listar().stream()
          .map(RecargaTransformador::trasnformar)
          .collect(Collectors.toList()
          );
          
    }


}