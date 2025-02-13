package com.axdesousa.beautique.services.impl;

import com.axdesousa.beautique.configurations.RabbitmqTopicConfig;
import com.axdesousa.beautique.services.BrockerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService implements BrockerService {

    private final ObjectMapper objectMapper;

    private final RabbitTemplate rabbitTemplate;

    private final RabbitmqTopicConfig rabbitmqTopicConfig;


    public RabbitMQService(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate, RabbitmqTopicConfig rabbitmqTopicConfig) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitmqTopicConfig = rabbitmqTopicConfig;
    }

    @Override
    public void send(String type, Object data) {
        String routingKey = type + ".#";
        try {
            String jsonData = objectMapper.writeValueAsString(data);
            rabbitTemplate.convertAndSend(rabbitmqTopicConfig.exchangeName,routingKey,jsonData,message -> {
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            });

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro serializing message " + e.getMessage());
        }
    }
}
