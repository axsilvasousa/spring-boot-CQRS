package com.axdesousa.ms_beautique_query.services.impl;

import com.axdesousa.ms_beautique_query.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_beautique_query.repositories.BeautyProcedureRepository;
import com.axdesousa.ms_beautique_query.services.BeautyProcedureService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeautyProcedureServiceImp implements BeautyProcedureService {
    private final BeautyProcedureRepository beautyProcedureRepository;

    public BeautyProcedureServiceImp(BeautyProcedureRepository beautyProcedureRepository) {
        this.beautyProcedureRepository = beautyProcedureRepository;
    }

    @Override
    public List<BeautyProcedureDto> getAllBeautyProcedures() {
        try{
            return beautyProcedureRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Beauty Procedure not found");
        }
    }

    @Override
    public List<BeautyProcedureDto> getBeautyProcedureByLikeName(String name) {
        try{
            return beautyProcedureRepository.getBeautyProcedureByLikeName(name);
        } catch (RuntimeException e) {
            throw new RuntimeException("Beauty Procedure not found by name" + e.getMessage());
        }
    }

    @Override
    public List<BeautyProcedureDto> getBeautyProcedureByLikeDescription(String description) {
        try{
            return beautyProcedureRepository.getBeautyProcedureByLikeDescription(description);
        } catch (Exception e) {
            throw new RuntimeException("Beauty Procedure not found by description" + e.getMessage());
        }
    }
}
