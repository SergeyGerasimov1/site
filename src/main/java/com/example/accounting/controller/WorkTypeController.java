package com.example.accounting.controller;

import com.example.accounting.controller.dto.WorkSectionDTO;
import com.example.accounting.controller.dto.WorkTypeDTO;
import com.example.accounting.controller.mapper.WorkSectionMapper;
import com.example.accounting.controller.mapper.WorkTypeMapper;
import com.example.accounting.service.WorkTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/work")
@RequiredArgsConstructor
public class WorkTypeController {
    private final WorkTypeService workTypeService;
    private final WorkSectionMapper sectionMapper;
    private final WorkTypeMapper typeMapper;

    @GetMapping("/sections")
    public List<WorkSectionDTO> getSection() {
        return sectionMapper.mapListToDTO(workTypeService.getAllSection());
    }

    @GetMapping("/types")
    public List<WorkTypeDTO> getTypes(@RequestParam Long sectionId) {
        return typeMapper.mapListToDTO(workTypeService.getTypes(sectionId));
    }
}