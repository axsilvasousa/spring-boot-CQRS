package com.axdesousa.ms_beautique_query.controllers;

import com.axdesousa.ms_beautique_query.dto.customers.CustomerDto;
import com.axdesousa.ms_beautique_query.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<CustomerDto>> getCustomerByLikeName(@PathVariable String name) {
        return ResponseEntity.ok().body(customerService.getCustomerByLikeName(name));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<CustomerDto>> getCustomerByLikeEmail(@PathVariable String email) {
        return ResponseEntity.ok().body(customerService.getCustomerByLikeEmail(email));
    }

}
