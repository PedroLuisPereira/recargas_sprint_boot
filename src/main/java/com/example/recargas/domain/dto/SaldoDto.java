package com.example.recargas.domain.dto;

public class SaldoDto {

    double valor;

    public SaldoDto() {
    }

    public SaldoDto(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "SaldoDto [valor=" + valor + "]";
    }


}
