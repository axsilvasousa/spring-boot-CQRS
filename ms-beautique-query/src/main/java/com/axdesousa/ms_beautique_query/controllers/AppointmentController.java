package com.axdesousa.ms_beautique_query.controllers;

import com.axdesousa.ms_beautique_query.dto.appointments.FullAppointmentDto;
import com.axdesousa.ms_beautique_query.services.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public ResponseEntity<List<FullAppointmentDto>> getAllAppointments() {
        return ResponseEntity.ok().body(appointmentService.getAllAppointments());
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<FullAppointmentDto>> getAppointmentCustomerId(@PathVariable  Long customerId) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentCustomerId(customerId));
    }

    @GetMapping("/beauty-procedure/{beautyProcedureId}")
    public ResponseEntity<List<FullAppointmentDto>> getAppointmentBeautyProcedureId(@PathVariable Long beautyProcedureId) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentBeautyProcedureId(beautyProcedureId));
    }
}
