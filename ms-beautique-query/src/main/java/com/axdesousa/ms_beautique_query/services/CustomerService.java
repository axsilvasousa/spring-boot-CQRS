package com.axdesousa.ms_beautique_query.services;

import com.axdesousa.ms_beautique_query.dto.customers.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();
    List<CustomerDto> getCustomerByLikeName(String name);
    List<CustomerDto> getCustomerByLikeEmail(String email);
}
