package com.example.recargas.infrastructure.adapters.output.message;

import org.springframework.stereotype.Service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

@Service
public class RabbitMQListner implements MessageListener {

    public void onMessage(Message message) {
        System.out.println("Consuming Message - " + new String(message.getBody()));
    }

}