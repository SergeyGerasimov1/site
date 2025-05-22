package com.example.accounting.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String executor;


    private LocalDate date;

    private String object;
    private String location;
    private String section;
    private String job;
    private boolean dismantling;
    private String material;
    private double amount;
    private double coef;
    private double weekendCoef;
    private String note;
}
