package com.axdesousa.ms_beautique_query.services;

import com.axdesousa.ms_beautique_query.dto.beautyProcedures.BeautyProcedureDto;

import java.util.List;

public interface BeautyProcedureService {
    List<BeautyProcedureDto> getAllBeautyProcedures();
    List<BeautyProcedureDto> getBeautyProcedureByLikeName(String name);
    List<BeautyProcedureDto> getBeautyProcedureByLikeDescription(String description);
}
