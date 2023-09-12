package com.example.recargas.infrastructure.output.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.recargas.domain.dto.CompraDto;
import com.example.recargas.domain.ports.RecargaRabbitMQ;

/**
 * Enviar mensaje en json
 */
@Service
public class RabbitMQJsonProducer implements RecargaRabbitMQ {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendCompra(CompraDto compraDto) {
        LOGGER.info(String.format("Json message sent -> %s", compraDto.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, compraDto);
    }

}
