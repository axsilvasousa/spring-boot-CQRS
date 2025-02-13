package com.axdesousa.ms_beautique_query.services.impl;

import com.axdesousa.ms_beautique_query.dto.customers.CustomerDto;
import com.axdesousa.ms_beautique_query.repositories.CustomerRepository;
import com.axdesousa.ms_beautique_query.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        try{
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Customer not found");
        }
    }

    @Override
    public List<CustomerDto> getCustomerByLikeName(String name) {
        try{
            return customerRepository.getCustomerByLikeName(name);
        } catch (RuntimeException e) {
            log.error(Arrays.toString(e.getStackTrace()));
            throw new RuntimeException("Customer not found by name" + e.getMessage());
        }
    }

    @Override
    public List<CustomerDto> getCustomerByLikeEmail(String email) {
        try{
            return customerRepository.getCustomerByLikeEmail(email);
        } catch (Exception e) {
            throw new RuntimeException("Customer not found by email" + e.getMessage());
        }
    }
}
