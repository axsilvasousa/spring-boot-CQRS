package com.axdesousa.ms_beautique_query.repositories;


import com.axdesousa.ms_beautique_query.dto.customers.CustomerDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface CustomerRepository extends MongoRepository<CustomerDto, Long> {


    @Query("{ 'name' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDto> getCustomerByLikeName(String name);

    @Query("{ 'email' : { $regex: ?0, $options: 'i' } }")
    List<CustomerDto> getCustomerByLikeEmail(String email);
}
