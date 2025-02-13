package com.axdesousa.ms_beautique_query.repositories;


import com.axdesousa.ms_beautique_query.dto.beautyProcedures.BeautyProcedureDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDto, Long> {

    @Query("{ 'name' : { $regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDto> getBeautyProcedureByLikeName(String name);

    @Query("{ 'description' : { $regex: ?0, $options: 'i' } }")
    List<BeautyProcedureDto> getBeautyProcedureByLikeDescription(String description);
}
