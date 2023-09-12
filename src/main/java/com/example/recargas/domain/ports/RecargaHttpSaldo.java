package com.example.recargas.domain.ports;

import com.example.recargas.domain.dto.SaldoDto;

public interface RecargaHttpSaldo {

    SaldoDto getSaldo(String operador);

}
