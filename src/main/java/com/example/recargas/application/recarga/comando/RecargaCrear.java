package com.example.recargas.application.recarga.comando;

import com.example.recargas.application.recarga.RecargaTransformador;
import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;
import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.service.RecargaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RecargaCrear {

    private final RecargaService recargaService;

    public RecargaCrear(RecargaService recargaService) {
        this.recargaService = recargaService;
    }

    @Transactional
    public RecargaRespuestaDto ejecutar(RecargaCrearDto recargaCrearDto) {
        RecargaSolicitudCrear recargaSolicitudCrear = RecargaTransformador.trasnformar(recargaCrearDto);
        Recarga recarga = recargaService.crear(recargaSolicitudCrear);
        return RecargaTransformador.trasnformar(recarga);
    }
}
