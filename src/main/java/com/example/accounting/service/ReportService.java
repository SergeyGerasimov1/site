package com.example.accounting.service;

import com.example.accounting.model.WorkReport;
import com.example.accounting.repo.WorkReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private WorkReportRepository repo;
    public List<WorkReport> getByExecutorAndDate(String executor, LocalDate from, LocalDate to)  {


        if (from != null && to != null) {
            return repo.findByExecutorAndDateBetween(executor, from, to);
        }
        return repo.findByExecutor(executor);
    }
}
