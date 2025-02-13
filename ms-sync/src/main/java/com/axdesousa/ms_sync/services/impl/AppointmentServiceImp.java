package com.axdesousa.ms_sync.services.impl;

import com.axdesousa.ms_sync.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_sync.dto.customers.CustomerDto;
import com.axdesousa.ms_sync.repositories.AppointmentRepository;
import com.axdesousa.ms_sync.services.AppointmentService;
import com.axdesousa.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppointmentServiceImp implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAppointment(FullAppointmentDto appointmentDto) {
        try {
            SyncLogger.info("Saving appointment: " + appointmentDto.getId());
            appointmentRepository.save(appointmentDto);
        } catch (Exception e) {
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateCustomerAppointments(CustomerDto customerDto) {
        try {
            SyncLogger.info("Updating customer appointments: " + customerDto.getId());
            Query query = new Query(Criteria.where("customer.id").is(customerDto.getId()));
            Update update = new Update().set("customer", customerDto);
            mongoTemplate.updateMulti(query, update, FullAppointmentDto.class);
        } catch (Exception e) {
            SyncLogger.error("Error updating customer appointments: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateBeautyProcedureAppointments(BeautyProcedureDto beautyProcedureDto) {
        try {
            SyncLogger.info("Updating beauty procedure appointments: " + beautyProcedureDto.getId());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedureDto.getId()));
            Update update = new Update()
                    .set("beautyProcedure.name", beautyProcedureDto.getName())
                    .set("beautyProcedure.description", beautyProcedureDto.getDescription());
            mongoTemplate.updateMulti(query, update, FullAppointmentDto.class);
        } catch (Exception e) {
            SyncLogger.error("Error updating beauty procedure appointments: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
