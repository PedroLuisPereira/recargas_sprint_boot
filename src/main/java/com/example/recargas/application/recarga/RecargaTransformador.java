package com.example.recargas.application.recarga;

import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;
import com.example.recargas.domain.dto.RecargaSolicitudCrear;

import com.example.recargas.domain.model.Recarga;

public class RecargaTransformador {

  private RecargaTransformador() {
  }

  public static RecargaSolicitudCrear trasnformar(RecargaCrearDto recargaCrearDto) {

    return new RecargaSolicitudCrear(
        recargaCrearDto.getValor(),
        recargaCrearDto.getCelular(),
        recargaCrearDto.getOperador(),
        recargaCrearDto.getPersonaId());

  }

  public static RecargaRespuestaDto trasnformar(Recarga recarga) {
    return new RecargaRespuestaDto(
        recarga.getId(),
        recarga.getValor(),
        recarga.getCelular(),
        recarga.getOperador(),
        recarga.getPersonaId());
  }
}
