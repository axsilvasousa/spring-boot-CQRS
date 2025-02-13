package com.axdesousa.beautique.services.impl;

import com.axdesousa.beautique.dtos.AppointmentDto;

import com.axdesousa.beautique.dtos.BeautyProcedureDto;
import com.axdesousa.beautique.dtos.CustomerDto;
import com.axdesousa.beautique.dtos.FullAppointmentDto;
import com.axdesousa.beautique.entities.AppointmentEntity;

import com.axdesousa.beautique.entities.BeautyProcedureEntity;
import com.axdesousa.beautique.entities.CustomerEntity;
import com.axdesousa.beautique.repositories.AppointmentRepository;
import com.axdesousa.beautique.repositories.BeautyProcedureRepository;
import com.axdesousa.beautique.repositories.CustomerRepository;
import com.axdesousa.beautique.services.AppointmentService;
import com.axdesousa.beautique.services.BrockerService;
import com.axdesousa.beautique.utils.ConvertMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository repository;

    @Autowired
    private BrockerService brockerService;

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper = new ModelMapper();

    private final ConvertMapper<AppointmentEntity, AppointmentDto> convertMapper = new ConvertMapper<>(AppointmentEntity.class, AppointmentDto.class);


    @Override
    public AppointmentDto create(AppointmentDto dto) {
        AppointmentEntity entity = convertMapper.convertToEntity(dto);
        AppointmentEntity payload =  repository.save(entity);
        sendAppointmentToQueue(payload);
        return convertMapper.convertToDto(payload);
    }

    @Override
    public void delete(Long id) {
        repository.findById(id).orElseThrow(()->new RuntimeException("Agendamento não encontrado"));
        repository.deleteById(id);
    }

    @Override
    public AppointmentDto update(AppointmentDto dto) {
        Optional<AppointmentEntity> model = repository.findById(dto.getId());
        if(model.isEmpty()){
            throw new RuntimeException("Customer não encontrado!");
        }
        AppointmentEntity payload =  convertMapper.convertToEntity(dto);
        payload.setCreatedAt(model.get().getCreatedAt());
        AppointmentEntity appointment = repository.save(payload);
        sendAppointmentToQueue(appointment);
        return convertMapper.convertToDto(appointment);
    }

    @Override
    public AppointmentDto setCustomAppointment(AppointmentDto dto) {
        AppointmentEntity model = getAppointment(dto.getId());
        model.setCustomer(getCustomer(dto.getCustomer()));
        model.setBeautyProcedure(getBeautyProcedure(dto.getBeautyProcedure()));
        model.setAppointmentsOpen(false);
        AppointmentEntity payload = repository.save(model);
        sendAppointmentToQueue(payload);
        return buildAppointmentDto(payload);
    }

    private AppointmentEntity getAppointment(Long id){
        return repository.findById(id).orElseThrow(()->new RuntimeException("Procedimento não encontrado!"));
    }

    private BeautyProcedureEntity getBeautyProcedure(Long id){
        return beautyProcedureRepository.findById(id).orElseThrow(()->new RuntimeException("Procedimento não encontrado!"));
    }

    private CustomerEntity getCustomer(Long id){
        return customerRepository.findById(id).orElseThrow(()->new RuntimeException("Customer não encontrado!"));
    }

    private AppointmentDto buildAppointmentDto(AppointmentEntity appointment){
        return AppointmentDto.builder()
                .id(appointment.getId())
                .beautyProcedure(appointment.getBeautyProcedure().getId())
                .customer(appointment.getCustomer().getId())
                .dateTime(appointment.getDateTime())
                .appointmentsOpen(appointment.getAppointmentsOpen())
                .build();

    }

    private void sendAppointmentToQueue(AppointmentEntity appointmentEntity){
        CustomerDto customerDto = appointmentEntity.getCustomer() != null ?
                modelMapper.map(appointmentEntity.getCustomer(), CustomerDto.class) :
                null;

        BeautyProcedureDto beautyProcedureDto = appointmentEntity.getBeautyProcedure() != null
                ? modelMapper.map(appointmentEntity.getBeautyProcedure(), BeautyProcedureDto.class)
                : null;

        FullAppointmentDto appointmentDto = FullAppointmentDto.builder()
                .id(appointmentEntity.getId())
                .dateTime(appointmentEntity.getDateTime())
                .appointmentsOpen(appointmentEntity.getAppointmentsOpen())
                .customer(customerDto)
                .beautyProcedure(beautyProcedureDto)
                .build();

        brockerService.send("appointment", appointmentDto);

    }


}
