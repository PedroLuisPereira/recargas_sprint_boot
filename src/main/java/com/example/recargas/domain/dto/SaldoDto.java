package com.example.recargas.domain.dto;

public class SaldoDto {

    double id;

    public SaldoDto() {
    }

    public SaldoDto(double id) {
        this.id = id;
    }

    public double getid() {
        return id;
    }

    public void setid(double id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "idDto [id=" + id + "]";
    }


}
