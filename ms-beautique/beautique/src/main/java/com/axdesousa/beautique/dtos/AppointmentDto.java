package com.axdesousa.beautique.dtos;

import com.axdesousa.beautique.entities.BeautyProcedureEntity;
import com.axdesousa.beautique.entities.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;

    private Long customer;
    private Long beautyProcedure;
}
