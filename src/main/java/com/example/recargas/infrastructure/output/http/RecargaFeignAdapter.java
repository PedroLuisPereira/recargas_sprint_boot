package com.example.recargas.infrastructure.output.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.recargas.application.recarga.dto.RecargaSaldoDto;
import com.example.recargas.domain.dto.SaldoDto;
import com.example.recargas.domain.ports.RecargaSaldoHttp;
import com.example.recargas.infrastructure.input.http.resilience4j.CircuitBreakerController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
@Primary

public class RecargaFeignAdapter implements RecargaSaldoHttp {

    Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    private final SaldoFeignClient saldoFeignClient;

    public RecargaFeignAdapter(SaldoFeignClient saldoFeignClient) {
        this.saldoFeignClient = saldoFeignClient;
    }

    @Override
    @CircuitBreaker(name = "getInvoiceCB", fallbackMethod = "getInvoiceFallback") 
    public SaldoDto getSaldo(String operador) {

        RecargaSaldoDto v = saldoFeignClient.getSaldo().stream()
                .filter(o -> o.getOperador().equals(operador))
                .findFirst()
                .orElse(new RecargaSaldoDto(1L, 0, operador));

        return new SaldoDto(v.getValor());

    }

    public SaldoDto getInvoiceFallback(Exception e) {
        logger.info("---Falla en la consulta de saldo---");

        return new SaldoDto(0);
     }

}
