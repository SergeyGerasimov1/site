package com.example.accounting.controller;

import com.example.accounting.model.WorkReport;
import com.example.accounting.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.example.accounting.repo.WorkReportRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class ReportController {

    private ReportService reportService;
    private WorkReportRepository repo;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody WorkReport report) {
        repo.save(report);
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
        return repo.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReport(@PathVariable Long id, @RequestBody WorkReport updated) {
        return repo.findById(id)
                .map(r -> {
                    r.setDate(updated.getDate());
                    r.setJob(updated.getJob());
                    r.setAmount(updated.getAmount());
                    r.setCoef(updated.getCoef());
                    r.setWeekendCoef(updated.getWeekendCoef());
                    repo.save(r);
                    return ResponseEntity.ok().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}