package com.axdesousa.beautique.controllers;

import com.axdesousa.beautique.dtos.CustomerDto;
import com.axdesousa.beautique.entities.CustomerEntity;
import com.axdesousa.beautique.services.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @PostMapping()
    public ResponseEntity<CustomerDto> create(@RequestBody CustomerDto customerDto){
        CustomerDto customer = customerService.create(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping()
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto){
        CustomerDto customer = customerService.update(customerDto);
        return ResponseEntity.ok(customer);
    }

    @GetMapping()
    public  ResponseEntity<String> get(){
        return ResponseEntity.ok("OK");
    }
}
