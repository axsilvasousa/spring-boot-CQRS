package com.axdesousa.beautique.services.impl;

import com.axdesousa.beautique.dtos.BeautyProcedureDto;
import com.axdesousa.beautique.entities.BeautyProcedureEntity;
import com.axdesousa.beautique.repositories.BeautyProcedureRepository;
import com.axdesousa.beautique.services.BeautyProceduresService;
import com.axdesousa.beautique.services.BrockerService;
import com.axdesousa.beautique.utils.ConvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BeautyProceduresServiceImp implements BeautyProceduresService {
    @Autowired
    private BeautyProcedureRepository repository;

    @Autowired
    private BrockerService brockerService;

    private final ConvertMapper<BeautyProcedureEntity, BeautyProcedureDto> convertMapper = new ConvertMapper<>(BeautyProcedureEntity.class, BeautyProcedureDto.class);


    @Override
    public BeautyProcedureDto create(BeautyProcedureDto dto) {
        BeautyProcedureEntity entity = convertMapper.convertToEntity(dto);
        BeautyProcedureEntity payload =  repository.save(entity);
        sendBeautyProcedureToQueue(payload);
        return convertMapper.convertToDto(payload);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProcedureEntity> entity = repository.findById(id);
        if(entity.isEmpty()){
            throw new RuntimeException("Customer não encontrado!");
        }
        repository.deleteById(id);
    }

    @Override
    public BeautyProcedureDto update(BeautyProcedureDto dto) {
        Optional<BeautyProcedureEntity> model = repository.findById(dto.getId());
        if(model.isEmpty()){
            throw new RuntimeException("Customer não encontrado!");
        }
        BeautyProcedureEntity payload =  convertMapper.convertToEntity(dto);
        payload.setAppointments(model.get().getAppointments());
        payload.setCreatedAt(model.get().getCreatedAt());
        BeautyProcedureEntity beautyProcedureEntity = repository.save(payload);
        sendBeautyProcedureToQueue(beautyProcedureEntity);
        return convertMapper.convertToDto(beautyProcedureEntity);
    }

    private void sendBeautyProcedureToQueue(BeautyProcedureEntity beautyProcedureEntity){
        BeautyProcedureDto beautyProcedureDto = BeautyProcedureDto.builder()
                .id(beautyProcedureEntity.getId())
                .name(beautyProcedureEntity.getName())
                .price(beautyProcedureEntity.getPrice())
                .description(beautyProcedureEntity.getDescription())
                .build();
        brockerService.send("beautyProcedure", beautyProcedureDto);
    }
}
