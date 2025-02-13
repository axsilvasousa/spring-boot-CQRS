package com.axdesousa.beautique.services;

import com.axdesousa.beautique.dtos.CustomerDto;
import com.axdesousa.beautique.entities.CustomerEntity;

public interface CustomerService {
    CustomerDto create(CustomerDto customerEntity);
    void delete(Long id);
    CustomerDto update(CustomerDto customerEntity);

}
