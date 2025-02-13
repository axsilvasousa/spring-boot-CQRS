package com.axdesousa.beautique.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class AppointmentEntity extends BaseEntity{

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private Boolean appointmentsOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beauty_procedure_id", nullable = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private BeautyProcedureEntity beautyProcedure;



}
