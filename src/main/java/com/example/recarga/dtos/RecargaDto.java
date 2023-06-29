package com.example.recarga.dtos;


public class RecargaDto {

    private Long id;
    private double valor;
    private String celular;
    private String operador;
    private Long persona_id;

    public RecargaDto(double valor, String celular, String operador, Long persona_id) {
        this.valor = valor;
        this.celular = celular;
        this.operador = operador;
        this.persona_id = persona_id;
    }


    public RecargaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public Long getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(Long persona_id) {
        this.persona_id = persona_id;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Override
    public String toString() {
        return "RecargaDto{" +
                "id=" + id +
                ", valor=" + valor +
                ", celular='" + celular + '\'' +
                ", operador='" + operador + '\'' +
                ", persona_id=" + persona_id +
                '}';
    }
}
