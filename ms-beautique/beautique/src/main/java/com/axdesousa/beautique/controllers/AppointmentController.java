package com.axdesousa.beautique.controllers;


import com.axdesousa.beautique.dtos.AppointmentDto;
import com.axdesousa.beautique.dtos.BeautyProcedureDto;
import com.axdesousa.beautique.services.impl.AppointmentServiceImpl;
import com.axdesousa.beautique.services.impl.BeautyProceduresServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentServiceImpl serviceImp;

    @PostMapping()
    public ResponseEntity<AppointmentDto> create(@RequestBody AppointmentDto dto){
        AppointmentDto payload = serviceImp.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(payload);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceImp.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping()
    public ResponseEntity<AppointmentDto> update(@RequestBody AppointmentDto dto){
        AppointmentDto payload = serviceImp.update(dto);
        return ResponseEntity.ok(payload);
    }

    @PutMapping()
    public ResponseEntity<AppointmentDto> marcaAgendamento(@RequestBody AppointmentDto dto){
        AppointmentDto payload = serviceImp.setCustomAppointment(dto);
        return ResponseEntity.ok(payload);
    }

}
