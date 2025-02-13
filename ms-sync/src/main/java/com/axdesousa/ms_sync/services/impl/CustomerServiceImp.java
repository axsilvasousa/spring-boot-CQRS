package com.axdesousa.ms_sync.services.impl;

import com.axdesousa.ms_sync.dto.customers.CustomerDto;
import com.axdesousa.ms_sync.repositories.CustomerRepository;
import com.axdesousa.ms_sync.services.CustomerService;
import com.axdesousa.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void updateCustomer(CustomerDto customerDto) {
        try {
            SyncLogger.info("Updating customer: " + customerDto.getId());
            customerRepository.save(customerDto);
        } catch (Exception e) {
            SyncLogger.error("Error updating customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
