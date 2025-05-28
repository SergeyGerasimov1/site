package com.example.accounting.controller;

import com.example.accounting.controller.dto.WorkReportDTO;
import com.example.accounting.controller.mapper.WorkReportMapper;
import com.example.accounting.model.WorkReport;
import com.example.accounting.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;
    private final WorkReportMapper mapper;

    @PostMapping
    public void save(@RequestBody WorkReportDTO reportDTO) {
        WorkReport report = mapper.mapToEntity(reportDTO);
        reportService.save(report, reportDTO.getJobId());
    }

    @GetMapping("/user/{executor}") //TODO
    public List<WorkReportDTO> getByExecutorAndDate(
            @PathVariable String executor,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    ) {
        return mapper.mapListToDTO(reportService.getByExecutorAndDate(executor, from, to));
    }

    @GetMapping("/{id}")
    public WorkReportDTO getById(@PathVariable Long id) {
        return mapper.mapToDto(reportService.getById(id));
    }

    @PutMapping("/{id}")
    public void updateReport(@RequestBody WorkReportDTO updated) {
        WorkReport workReport = mapper.mapToEntity(updated);
        reportService.updateReport(workReport);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reportService.delete(id);
    }
}