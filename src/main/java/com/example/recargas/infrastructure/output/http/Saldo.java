package com.example.recargas.infrastructure.output.http;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.recargas.application.recarga.dto.RecargaSaldoDto;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.ports.HttpSaldo;

@Service
@Primary
public class Saldo implements HttpSaldo {

    private final SaldoFeignClient saldoFeignClient;

    public Saldo(SaldoFeignClient saldoFeignClient) {
        this.saldoFeignClient = saldoFeignClient;
    }

    @Override
    public SaldoDto getSaldo(String operador) {

        RecargaSaldoDto v = saldoFeignClient.getSaldo().stream()
                .filter(o -> o.getOperador().equals(operador))
                .findFirst()
                .orElse(new RecargaSaldoDto(1L, 0, operador));

        return new SaldoDto(v.getValor());

    }

}
