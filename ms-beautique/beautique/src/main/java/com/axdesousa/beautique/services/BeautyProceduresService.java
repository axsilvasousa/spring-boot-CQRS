package com.axdesousa.beautique.services;

import com.axdesousa.beautique.dtos.BeautyProcedureDto;


public interface BeautyProceduresService {
    BeautyProcedureDto create(BeautyProcedureDto customerEntity);
    void delete(Long id);
    BeautyProcedureDto update(BeautyProcedureDto customerEntity);
}
