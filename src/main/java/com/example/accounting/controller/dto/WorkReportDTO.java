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
    private String section;
    private String job;
    private boolean dismantling;
    private String material;
    private double amount;
    private double coef;
    private double weekendCoef;
    private String note;

}
