package com.example.accounting.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class WorkReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String executor;
    private LocalDate date;
    private String object;
    private String location;
    private boolean dismantling;
    private String material;
    private double amount;
    private double coef;
    private String note;
    private double salary;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private WorkType job;
}
