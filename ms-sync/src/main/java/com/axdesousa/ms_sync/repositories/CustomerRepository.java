package com.axdesousa.ms_sync.repositories;

import com.axdesousa.ms_sync.dto.customers.CustomerDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<CustomerDto, Long> {
}
