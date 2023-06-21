package com.example.recarga.models;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "recargas")
public class Recarga {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "operador", nullable = false)
    private String operador;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Persona persona;

    public Recarga() {
    }

    public Recarga(Long id, double valor, String operador, Persona persona) {
        this.id = id;
        this.valor = valor;
        this.operador = operador;
        this.persona = persona;
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Recarga{" +
                "id=" + id +
                ", valor=" + valor +
                ", operador='" + operador + '\'' +
                ", persona=" + persona +
                '}';
    }
}
