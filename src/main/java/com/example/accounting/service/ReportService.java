package com.example.accounting.service;

import com.example.accounting.model.WorkReport;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<WorkReport> getByExecutorAndDate(String executor, LocalDate from, LocalDate to);

    void save(WorkReport report, Long jobId);

    WorkReport getById(Long id);

    void updateReport(WorkReport updated);

    void delete(Long id);

}
