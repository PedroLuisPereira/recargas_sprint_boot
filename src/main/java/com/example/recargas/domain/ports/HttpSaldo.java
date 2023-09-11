package com.example.recargas.domain.ports;

import com.example.recargas.domain.dto.SaldoDto;

public interface HttpSaldo {

    SaldoDto getSaldo(String operador);

}
