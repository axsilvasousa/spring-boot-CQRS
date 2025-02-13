package com.axdesousa.ms_sync.services.impl;

import com.axdesousa.ms_sync.dto.beautyProcedures.BeautyProcedureDto;
import com.axdesousa.ms_sync.repositories.BeautyProcedureRepository;
import com.axdesousa.ms_sync.services.BeautyProcedureService;
import com.axdesousa.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImp implements BeautyProcedureService {
    @Autowired
    private BeautyProcedureRepository beautyProceduresRepository;

    @Override
    public void updateBeautyProcedure(BeautyProcedureDto beautyProcedureDto) {
        try{
            SyncLogger.info("Updating beauty procedure: " + beautyProcedureDto.getId());
            beautyProceduresRepository.save(beautyProcedureDto);
        } catch (Exception e) {
            SyncLogger.error("Error updating beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
