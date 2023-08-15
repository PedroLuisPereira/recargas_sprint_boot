package com.example.recargas.domain.model;

import com.example.recargas.domain.validation.Validacion;

import java.io.Serializable;

public class Persona implements Serializable {

    private Long id;
    private final String nombre;
    private final String email;

    private Persona(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    private Persona(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    public static Persona getInstance(String nombre, String email) {
        Validacion.validarObligatorio(nombre, "El campo nombre es obligatorio");
        Validacion.validarObligatorio(email, "El campo email es obligatorio");
        return new Persona(nombre, email);
    }

    public static Persona getInstance(long id, String nombre, String email) {
        Validacion.validarIntMayorQueCero(id, "Id es obligatorio");
        Validacion.validarObligatorio(nombre, "El campo nombre es obligatorio");
        Validacion.validarObligatorio(email, "El campo email es obligatorio");
        return new Persona(id, nombre, email);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Persona{" +
          "id=" + id +
          ", nombre='" + nombre + '\'' +
          ", email='" + email + '\'' +
          '}';
    }
}
