package com.axdesousa.ms_sync.repositories;

import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDto, Long> {
}
