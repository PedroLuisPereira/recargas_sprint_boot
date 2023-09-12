package com.example.recargas.domain.ports;

import com.example.recargas.domain.dto.SaldoDto;

public interface RecargaSaldoHttp {

    SaldoDto getSaldo(String operador);

}
