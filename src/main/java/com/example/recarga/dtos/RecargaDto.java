package com.example.recarga.dtos;


public class RecargaDto {

    private Long id;
    private double valor;
    private String operador;
    private Long persona_id;

    public RecargaDto(double valor, String operador, Long persona_id) {
        this.valor = valor;
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


    @Override
    public String toString() {
        return "RecargaDto [id=" + id + ", valor=" + valor + ", operador=" + operador + ", persona_id=" + persona_id
                + "]";
    }

}
