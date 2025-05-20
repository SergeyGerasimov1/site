package com.example.accounting.controller;

import com.example.accounting.model.WorkReport;
import com.example.accounting.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.accounting.repository.WorkReportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    private final WorkReportRepository repo;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody WorkReport report) {
        reportService.save(report);
        return new ResponseEntity<>("Сохранено", HttpStatus.OK);
    }
    @GetMapping("/user/{executor}")
    public List<WorkReport> getByExecutorAndDate(
            @PathVariable String executor,
            @RequestParam(required = false) LocalDate from,
            @RequestParam(required = false) LocalDate to
    )  {
        return reportService.getByExecutorAndDate(executor, from, to);
    }
    @GetMapping("/{id}")
    public Optional<WorkReport> getById(@PathVariable Long id) {
        return reportService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable Long id, @RequestBody WorkReport updated) {
        return reportService.updateReport(id,updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reportService.delete(id);
    }
}