package com.example.recargas.domain.model;

import com.example.recargas.domain.dto.Operador;
import com.example.recargas.domain.exception.CampoConException;
import com.example.recargas.domain.validation.Validacion;

import java.io.Serializable;
import java.util.Arrays;

public class Recarga implements Serializable {

    private static final int[] CLARO = {310, 311, 312, 313, 314, 320, 321, 322, 323};
    private static final int[] MOVISTART = {315, 316, 317, 318, 319};
    private static final int[] TIGO = {300, 301, 302, 303, 304, 324};

    private Long id;

    private double valor;

    private String celular;

    private String operador;

    private long personaId;

    public Recarga() {
    }

    private Recarga(Long id, double valor, String celular, String operador, long personaId) {
        this.id = id;
        this.valor = valor;
        this.celular = celular;
        this.operador = operador;
        this.personaId = personaId;
    }

    public static Recarga getInstance(long id, double valor, String celular, String operador, long personaId) {

        Validacion.validarMayorQueCero(valor, "El campo valor es obligatorio");
        Validacion.validarObligatorio(celular, "El campo celular es obligatorio");
        Validacion.validarSeaNumerico(celular, "El numero de celular errado");
        Validacion.validarObligatorio(operador, "El campo operador es obligatorio");
        //Validacion.validarMayorQueCero((int) personaId, "El campo personaId es obligatorio");

        validarCelular(celular);
        validarOperador(operador);
        validarOperadorCelular(operador, celular);

        return new Recarga(id, valor, celular, operador, personaId);
    }

    public static Recarga getInstance(double valor, String celular, String operador, long personaId) {

        Validacion.validarMayorQueCero(valor, "El campo valor es obligatorio");
        Validacion.validarObligatorio(celular, "El campo celular es obligatorio");
        Validacion.validarSeaNumerico(celular, "El numero de celular errado");
        Validacion.validarObligatorio(operador, "El campo operador es obligatorio");
        //Validacion.validarMayorQueCero((int) personaId, "El campo personaId es obligatorio");

        validarCelular(celular);
        validarOperador(operador);
        validarOperadorCelular(operador, celular);

        return new Recarga(null, valor, celular, operador, personaId);
    }

    private static void validarCelular(String celular) {
        if (celular.length() != 10) {
            throw new CampoConException("Celular incorrecto");
        }
    }

    private static void validarOperador(String operador) {
        long total = Arrays.stream(Operador.values())
          .filter(valor -> valor.name().equals(operador.toUpperCase()))
          .count();

        if (total == 0) {
            throw new CampoConException("Operador incorrecto");
        }
    }

    private static void validarOperadorCelular(String operador, String celular) {

        int prefijo = Integer.parseInt(celular.substring(0, 3));
        int[] prefijosOperador;

        if (operador.equalsIgnoreCase("TIGO")) {
            prefijosOperador = TIGO;
        } else if (operador.equalsIgnoreCase("CLARO")) {
            prefijosOperador = CLARO;
        } else {
            prefijosOperador = MOVISTART;
        }

        long total = Arrays.stream(prefijosOperador)
          .filter(value -> value == prefijo)
          .count();

        if (total == 0) {
            throw new CampoConException("Celular no corresponde al operador");
        }
    }

    public Long getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getCelular() {
        return celular;
    }

    public String getOperador() {
        return operador;
    }

    public long getPersonaId() {
        return personaId;
    }

    @Override
    public String toString() {
        return "Recarga [id=" + id + ", valor=" + valor + ", celular=" + celular + ", operador=" + operador
          + ", personaId=" + personaId + "]";
    }

}
