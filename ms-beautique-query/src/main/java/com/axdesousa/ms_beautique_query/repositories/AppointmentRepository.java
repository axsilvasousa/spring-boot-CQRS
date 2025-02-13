package com.axdesousa.ms_beautique_query.repositories;


import com.axdesousa.ms_beautique_query.dto.appointments.FullAppointmentDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface AppointmentRepository extends MongoRepository<FullAppointmentDto, Long> {
    @Query("{ 'customerId' : ?0 }")
    List<FullAppointmentDto> getAppointmentCustomerId(Long customerId);
    @Query("{ 'beautyProcedureId' : ?0 }")
    List<FullAppointmentDto> getAppointmentBeautyProcedureId(Long beautyProcedureId);

}
