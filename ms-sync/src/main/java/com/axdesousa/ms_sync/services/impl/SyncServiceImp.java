package com.axdesousa.ms_sync.services.impl;

import com.axdesousa.ms_sync.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_sync.dto.customers.CustomerDto;
import com.axdesousa.ms_sync.services.AppointmentService;
import com.axdesousa.ms_sync.services.BeautyProcedureService;
import com.axdesousa.ms_sync.services.CustomerService;
import com.axdesousa.ms_sync.services.SyncService;
import com.axdesousa.ms_sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImp implements SyncService {

    private final AppointmentService appointmentService;
    private final BeautyProcedureService beautyProcedureService;
    private final CustomerService customerService;

    public SyncServiceImp(AppointmentService appointmentService, BeautyProcedureService beautyProcedureService, CustomerService customerService) {
        this.appointmentService = appointmentService;
        this.beautyProcedureService = beautyProcedureService;
        this.customerService = customerService;
    }

    @Override
    public void syncCustomer(CustomerDto customerDto) {
        try {
            SyncLogger.info("Syncing customer: " + customerDto.getId());
            customerService.updateCustomer(customerDto);
            SyncLogger.info("Syncing customer appointments: " + customerDto.getId());
            appointmentService.updateCustomerAppointments(customerDto);
        } catch (Exception e) {
            SyncLogger.error("Error syncing customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDto appointmentDto) {
        try {
            SyncLogger.info("Syncing appointment: " + appointmentDto.getId());
            appointmentService.saveAppointment(appointmentDto);
        } catch (Exception e) {
            SyncLogger.error("Error syncing appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedure(BeautyProcedureDto beautyProcedureDto) {
        try {
            SyncLogger.info("Syncing beauty procedure: " + beautyProcedureDto.getId());
            beautyProcedureService.updateBeautyProcedure(beautyProcedureDto);
            SyncLogger.info("Syncing beauty procedure appointments: " + beautyProcedureDto.getId());
            appointmentService.updateBeautyProcedureAppointments(beautyProcedureDto);
        } catch (Exception e) {
            SyncLogger.error("Error syncing beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
