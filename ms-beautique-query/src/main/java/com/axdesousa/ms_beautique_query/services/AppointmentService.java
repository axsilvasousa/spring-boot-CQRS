package com.axdesousa.ms_beautique_query.services;

import com.axdesousa.ms_beautique_query.dto.appointments.FullAppointmentDto;

import java.util.List;

public interface AppointmentService {
    List<FullAppointmentDto> getAllAppointments();
    List<FullAppointmentDto> getAppointmentCustomerId(Long customerId);
    List<FullAppointmentDto> getAppointmentBeautyProcedureId(Long beautyProcedureId);


}
