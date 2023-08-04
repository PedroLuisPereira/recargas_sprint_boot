package com.example.recargas.domain.dto;


public class RecargaSolicitudCrear {

    private double valor;

    private String celular;

    private String operador;

    private long personaId;

    public RecargaSolicitudCrear() {
    }

    public RecargaSolicitudCrear(double valor, String celular, String operador, long personaId) {
        this.valor = valor;
        this.celular = celular;
        this.operador = operador;
        this.personaId = personaId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(long personaId) {
        this.personaId = personaId;
    }
}
