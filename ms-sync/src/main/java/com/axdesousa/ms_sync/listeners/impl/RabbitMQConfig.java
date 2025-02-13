package com.axdesousa.ms_sync.listeners.impl;

import com.axdesousa.ms_sync.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_sync.dto.customers.CustomerDto;
import com.axdesousa.ms_sync.listeners.ListenerConfig;
import com.axdesousa.ms_sync.services.SyncService;
import com.axdesousa.ms_sync.utils.SyncLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableRabbit
public class RabbitMQConfig implements ListenerConfig {



    private final ObjectMapper objectMapper;
    private final SyncService  syncService;

    public RabbitMQConfig(ObjectMapper objectMapper, SyncService syncService) {
        this.objectMapper = objectMapper;
        this.syncService = syncService;
    }

    @Override
    @RabbitListener(queues = "customerQueue")
    public void listenToCustomerQueue(String message) {
        try {
            CustomerDto customerDto = objectMapper.readValue(message, CustomerDto.class);
            syncService.syncCustomer(customerDto);
            SyncLogger.info("Received message from customerQueue: " + customerDto.toString());
        } catch (Exception e) {
            SyncLogger.error("Error while processing message from customerQueue: " + e.getMessage());
        }

    }

    @Override
    @RabbitListener(queues = "BeautyProcedureQueue")
    public void listenToBeautyProcedureQueue(String message) {
        try{
            BeautyProcedureDto beautyProcedureDto = objectMapper.readValue(message, BeautyProcedureDto.class);
            syncService.syncBeautyProcedure(beautyProcedureDto);
            SyncLogger.info("Received message from beautyProcedureQueue: " + beautyProcedureDto.toString());
        } catch (Exception e) {
            SyncLogger.error("Error while processing message from beautyProcedureQueue: " + e.getMessage());
        }
    }

    @Override
    @RabbitListener(queues = "appointmentQueue")
    public void listenToAppointmentQueue(String message) {
        try {
            FullAppointmentDto appointmentDto = objectMapper.readValue(message, FullAppointmentDto.class);
            syncService.syncAppointment(appointmentDto);
            SyncLogger.info("Received message from appointmentQueue: " + appointmentDto.toString());
        } catch (Exception e) {
            SyncLogger.error("Error while processing message from appointmentQueue: " + e.getMessage());
        }
    }
}
