package com.axdesousa.beautique.services.impl;

import com.axdesousa.beautique.dtos.CustomerDto;
import com.axdesousa.beautique.entities.CustomerEntity;
import com.axdesousa.beautique.repositories.CustomerRepository;
import com.axdesousa.beautique.services.BrockerService;
import com.axdesousa.beautique.services.CustomerService;
import com.axdesousa.beautique.utils.ConvertMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrockerService brockerService;

    private final ConvertMapper<CustomerEntity,CustomerDto> convertMapper = new ConvertMapper<>(CustomerEntity.class,CustomerDto.class);

    @Override
    public CustomerDto create(CustomerDto customerDto) {
        CustomerEntity entity = convertMapper.convertToEntity(customerDto);
        CustomerEntity customer =  customerRepository.save(entity);
        sendCustomerToQueue(customer);
        return convertMapper.convertToDto(customer);
    }

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if(customer.isEmpty()){
            throw new RuntimeException("Customer não encontrado!");
        }
        customerRepository.delete(customer.get());
    }

    @Override
    public CustomerDto update(CustomerDto customerDto) {
        Optional<CustomerEntity> customer = customerRepository.findById(customerDto.getId());
        if(customer.isEmpty()){
            throw new RuntimeException("Customer não encontrado!");
        }
        CustomerEntity customerEntity =  convertMapper.convertToEntity(customerDto);
        customerEntity.setAppointments(customer.get().getAppointments());
        customerEntity.setCreatedAt(customer.get().getCreatedAt());
        CustomerEntity payload = customerRepository.save(customerEntity);
        sendCustomerToQueue(payload);
        return convertMapper.convertToDto(payload);
    }

    private void sendCustomerToQueue(CustomerEntity customerEntity){
        CustomerDto customerDto = CustomerDto.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .build();
        brockerService.send("customer",customerDto);

    }
}
