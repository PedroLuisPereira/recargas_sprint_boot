package com.example.recargas.infrastructure.adapters.output.message;

import com.example.recargas.domain.model.Persona;
import com.example.recargas.domain.ports.RabbitMQPuerto;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender implements RabbitMQPuerto {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${javainuse.rabbitmq.exchange}")
    private String exchange;

    @Value("${javainuse.rabbitmq.routingkey}")
    private String routingkey;

    public void send(Persona company) {
        rabbitTemplate.convertAndSend(exchange, routingkey, company);
        System.out.println("Send msg = " + company);

    }

    @Override
    public void enviarMessage(Persona persona) {
        rabbitTemplate.convertAndSend(exchange, routingkey, persona);
        System.out.println("Send msg = " + persona);
    }
}