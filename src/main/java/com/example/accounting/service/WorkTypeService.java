package com.example.accounting.service;

import com.example.accounting.model.WorkSection;
import com.example.accounting.model.WorkType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkTypeService {
    public List<WorkSection> getAllSection();

    public List<WorkType> getTypes(Long sectionId);

    public WorkType getById(Long jobId);
}
