package com.axdesousa.ms_sync.services;

import com.axdesousa.ms_sync.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_sync.dto.customers.CustomerDto;

public interface SyncService {
    void syncAppointment(FullAppointmentDto appointmentDto);
    void syncCustomer(CustomerDto customerDto);
    void syncBeautyProcedure(BeautyProcedureDto beautyProcedureDto);
}
