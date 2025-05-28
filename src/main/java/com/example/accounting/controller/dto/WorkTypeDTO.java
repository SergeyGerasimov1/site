package com.example.accounting.controller.dto;

import com.example.accounting.model.WorkSection;
import lombok.Data;

@Data
public class WorkTypeDTO {
    private Long id;
    private String name;
    private double price;
    private String unit;
    private WorkSection section;
}
