package com.axdesousa.beautique.services;

import com.axdesousa.beautique.dtos.AppointmentDto;
import com.axdesousa.beautique.dtos.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public interface AppointmentService {
    AppointmentDto create(AppointmentDto dto);
    void delete(Long id);
    AppointmentDto update(AppointmentDto dto);
    AppointmentDto setCustomAppointment(AppointmentDto dto);
}
