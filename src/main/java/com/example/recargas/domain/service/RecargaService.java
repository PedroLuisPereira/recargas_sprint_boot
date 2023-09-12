package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.CompraDto;
import com.example.recargas.domain.dto.RecargaSolicitudCrear;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.exception.NoSaldoException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaRabbitMQ;
import com.example.recargas.domain.ports.RecargaHttpSaldo;
import com.example.recargas.domain.ports.PersonaRepository;
import com.example.recargas.domain.ports.RecargaRepository;

import java.util.List;

public class RecargaService {

    private final PersonaRepository personaRepository;
    private final RecargaRepository recargaRepository;
    private final RecargaHttpSaldo httpSaldo;
    private final RecargaRabbitMQ recargaRabbitMQ;

    public RecargaService(PersonaRepository personaRepository,
            RecargaRepository recargaRepository,
            RecargaHttpSaldo httpSaldo,
            RecargaRabbitMQ recargaRabbitMQ) {
        this.personaRepository = personaRepository;
        this.recargaRepository = recargaRepository;
        this.httpSaldo = httpSaldo;
        this.recargaRabbitMQ = recargaRabbitMQ;
    }

    public List<Recarga> listar() {
        return recargaRepository.list();
    }

    public Recarga crear(RecargaSolicitudCrear recargaSolicitudCrear) {

        Persona persona = personaRepository.listarByid(recargaSolicitudCrear.getPersonaId())
                .orElseThrow(() -> new RegistroNotFoundException("No se encontro id de la persona"));

        Recarga recarga = Recarga.getInstance(
                recargaSolicitudCrear.getValor(),
                recargaSolicitudCrear.getCelular(),
                recargaSolicitudCrear.getOperador(),
                persona);

        SaldoDto saldoDto = httpSaldo.getSaldo(recargaSolicitudCrear.getOperador());

        if (saldoDto != null && saldoDto.getValor() < recargaSolicitudCrear.getValor()) {
            throw new NoSaldoException("No se puede hacer la recarga saldo insuficiente");
        }

        recarga = recargaRepository.save(recarga);

        recargaRabbitMQ.sendCompra(new CompraDto(recarga.getOperador(), recarga.getValor()));

        return recarga;

    }

}
