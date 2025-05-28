package com.example.accounting.controller.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class WorkReportDTO {
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
    private Long jobId;
}
