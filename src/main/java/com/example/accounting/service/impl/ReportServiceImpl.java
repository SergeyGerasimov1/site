package com.example.accounting.service.impl;

import com.example.accounting.model.WorkReport;
import com.example.accounting.repository.WorkReportRepository;
import com.example.accounting.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final WorkReportRepository repo;
    @Override
    public List<WorkReport> getByExecutorAndDate(String executor, LocalDate from, LocalDate to) {
        if (from != null && to != null) {
            return repo.findByExecutorAndDateBetween(executor, from, to);
        }
        return repo.findByExecutor(executor);
    }
    public void save(WorkReport report) {
        repo.save(report);
    }
    public Optional<WorkReport> getById(Long id) {
        return repo.findById(id);
    }
    public ResponseEntity<?> updateReport(Long id, WorkReport updated) {
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
    public void delete(Long id) {
        repo.deleteById(id);
    }

}
