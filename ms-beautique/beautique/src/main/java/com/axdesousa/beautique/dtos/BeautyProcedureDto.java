package com.axdesousa.beautique.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeautyProcedureDto {
    private Long id;
    private String name;
    private String description;
    private String price;
}
