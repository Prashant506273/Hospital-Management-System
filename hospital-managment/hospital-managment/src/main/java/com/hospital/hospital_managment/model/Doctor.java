package com.hospital.hospital_managment.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String specialization;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    @Column(name = "available_days")
    private String availableDays;

    @Column(name = "consultation_fee")
    private Double consultationFee;
}