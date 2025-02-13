package com.axdesousa.ms_sync.dto.customers;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customers")
public class CustomerDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
