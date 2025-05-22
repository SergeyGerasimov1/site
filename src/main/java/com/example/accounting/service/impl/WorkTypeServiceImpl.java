package com.example.accounting.service.impl;

import com.example.accounting.model.WorkSection;
import com.example.accounting.model.WorkType;
import com.example.accounting.repository.WorkReportRepository;
import com.example.accounting.repository.WorkSectionRepository;
import com.example.accounting.repository.WorkTypeRepository;
import com.example.accounting.service.WorkTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class WorkTypeServiceImpl implements WorkTypeService {
    private final WorkTypeRepository workTypeRepository;
    private final WorkSectionRepository workSectionRepository;
    @Override
    public List<WorkSection> getAllSection() {
        return workSectionRepository.findAll();
    }

    @Override
    public List<WorkType> getTypes(Long sectionId) {
        return workTypeRepository.findBySectionId(sectionId);
    }
}
