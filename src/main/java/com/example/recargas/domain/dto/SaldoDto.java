package com.example.recargas.domain.dto;

public class SaldoDto {

    double saldo;

    public SaldoDto() {
    }

    public SaldoDto(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "SaldoDto [saldo=" + saldo + "]";
    }


}
