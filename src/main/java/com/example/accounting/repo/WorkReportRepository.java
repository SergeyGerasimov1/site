package com.example.accounting.repo;

import com.example.accounting.model.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {
    List<WorkReport> findByExecutor(String executor);
    List<WorkReport> findByExecutorAndDateBetween(String executor, LocalDate from, LocalDate to);
}