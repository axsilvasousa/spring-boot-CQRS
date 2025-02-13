package com.axdesousa.ms_sync.services;

import com.axdesousa.ms_sync.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_sync.dto.customers.CustomerDto;

public interface AppointmentService {
    void saveAppointment(FullAppointmentDto appointmentDto);
    void updateCustomerAppointments(CustomerDto customerDto);
    void updateBeautyProcedureAppointments(BeautyProcedureDto beautyProcedureDto);
}
