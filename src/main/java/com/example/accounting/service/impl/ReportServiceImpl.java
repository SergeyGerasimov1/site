package com.example.accounting.service.impl;

import com.example.accounting.model.WorkReport;
import com.example.accounting.repository.WorkReportRepository;
import com.example.accounting.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
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

    public WorkReport getById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public void updateReport(WorkReport updated) {
        repo.save(updated);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

}
