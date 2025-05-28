package com.example.accounting.controller.dto;

import com.example.accounting.model.WorkType;
import lombok.Data;

import java.util.List;

@Data
public class WorkSectionDTO {
    private Long id;
    private String name;
    private List<WorkType> workTypes;
}
