package com.axdesousa.ms_beautique_query.dto.beautyProcedures;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "beautyProcedures")
public class BeautyProcedureDto {
    private Long id;
    private String name;
    private String description;
    private String price;
}
