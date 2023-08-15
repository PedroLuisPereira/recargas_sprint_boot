package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.exception.NoSaldoException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.PersonaPuerto;
import com.example.recargas.domain.ports.RecargaPuerto;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class RecargaService {

    private final PersonaPuerto personaPuerto;
    private final RecargaPuerto recargaPuerto;
    
    private final RestTemplate restTemplate;


    public RecargaService(PersonaPuerto personaPuerto, RecargaPuerto recargaPuerto,  RestTemplate restTemplate
    ) {
        this.personaPuerto = personaPuerto;
        this.recargaPuerto = recargaPuerto;
        this.restTemplate = restTemplate;
    }

    public List<Recarga> listar(){
        return recargaPuerto.list();
    }

    public Recarga crear(RecargaSolicitudCrear recargaSolicitudCrear) {

        Persona persona = personaPuerto.listarByid(recargaSolicitudCrear.getPersonaId())
          .orElseThrow(() -> new RegistroNotFoundException("No se encontro id de la persona" )
          );

        Recarga recarga = Recarga.getInstance(
          recargaSolicitudCrear.getValor(),
          recargaSolicitudCrear.getCelular(),
          recargaSolicitudCrear.getOperador(),
          persona);


        //solicitud sincrona
        SaldoDto saldoDto = restTemplate.getForObject("https://random-data-api.com/api/v2/banks", SaldoDto.class);
        if (saldoDto != null && saldoDto.getid() * 10 < recargaSolicitudCrear.getValor()) {
            throw new NoSaldoException("No se puede hacer la recaraga no hay saldo");
        }

        return recargaPuerto.save(recarga);
    }

}
