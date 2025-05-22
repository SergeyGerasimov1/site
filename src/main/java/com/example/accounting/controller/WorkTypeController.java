package com.example.accounting.controller;

import com.example.accounting.model.WorkSection;
import com.example.accounting.model.WorkType;
import com.example.accounting.service.WorkTypeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class WorkTypeController {
    private final WorkTypeService workTypeService;
    @GetMapping("/sections")
    public List<WorkSection> getSection() {
        return workTypeService.getAllSection();
    }
    @GetMapping("/types")
    public List<WorkType> getTypes(@RequestParam Long sectionId) {
        return workTypeService.getTypes(sectionId);
    }
}
