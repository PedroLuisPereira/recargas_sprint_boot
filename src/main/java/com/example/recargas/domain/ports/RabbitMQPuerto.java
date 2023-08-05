package com.example.recargas.domain.ports;

import com.example.recargas.domain.model.Persona;

public interface RabbitMQPuerto {

    void enviarMessage(Persona persona);

}
