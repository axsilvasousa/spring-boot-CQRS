package com.axdesousa.ms_beautique_query.dto.appointments;

import com.axdesousa.ms_beautique_query.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_beautique_query.dto.customers.CustomerDto;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class FullAppointmentDto {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;

    private CustomerDto customer;
    private BeautyProcedureDto beautyProcedure;
}
