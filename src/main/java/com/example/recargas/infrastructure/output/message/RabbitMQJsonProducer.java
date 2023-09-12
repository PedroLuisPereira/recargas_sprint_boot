package com.example.recargas.infrastructure.output.message;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.recargas.domain.dto.CompraDto;
import com.example.recargas.domain.ports.CompraMensaje;
import com.example.recargas.infrastructure.config.User;

@Service
public class RabbitMQJsonProducer implements CompraMensaje {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }

    @Override
    public void sendCompra(CompraDto compraDto) {
        LOGGER.info(String.format("Json message sent -> %s", compraDto.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, compraDto);
    }

}
