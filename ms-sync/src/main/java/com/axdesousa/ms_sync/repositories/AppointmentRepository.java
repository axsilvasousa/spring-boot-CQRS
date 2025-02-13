package com.axdesousa.ms_sync.repositories;

import com.axdesousa.ms_sync.dto.appointments.FullAppointmentDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<FullAppointmentDto, Long> {
}
