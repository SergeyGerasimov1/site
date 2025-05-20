package com.example.accounting.service;

import com.example.accounting.model.WorkReport;
import com.example.accounting.repository.WorkReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface ReportService {
    public List<WorkReport> getByExecutorAndDate(String executor, LocalDate from, LocalDate to);
    public void save(WorkReport report);
    public Optional<WorkReport> getById(Long id);
    public ResponseEntity<?> updateReport(Long id, WorkReport updated);
    public void delete(Long id);
}
