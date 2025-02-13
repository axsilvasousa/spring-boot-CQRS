package com.axdesousa.ms_beautique_query.services.impl;

import com.axdesousa.ms_beautique_query.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_beautique_query.repositories.AppointmentRepository;
import com.axdesousa.ms_beautique_query.services.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImp implements AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImp(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<FullAppointmentDto> getAllAppointments() {
        try{
            return appointmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Appointment not found");
        }
    }

    @Override
    public List<FullAppointmentDto> getAppointmentCustomerId(Long customerId) {
        try{
            return appointmentRepository.getAppointmentCustomerId(customerId);
        } catch (Exception e) {
            throw new RuntimeException("Appointment not found by customer id");
        }
    }

    @Override
    public List<FullAppointmentDto> getAppointmentBeautyProcedureId(Long beautyProcedureId) {
        try{
            return appointmentRepository.getAppointmentBeautyProcedureId(beautyProcedureId);
        } catch (Exception e) {
            throw new RuntimeException("Appointment not found by beauty procedure id");
        }
    }
}
