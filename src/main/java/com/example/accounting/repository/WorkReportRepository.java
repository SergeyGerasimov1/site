package com.example.accounting.repository;

import com.example.accounting.model.WorkReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkReportRepository extends JpaRepository<WorkReport, Long> {

    List<WorkReport> findByExecutor(String executor);

    List<WorkReport> findByExecutorAndDateBetween(String executor, LocalDate from, LocalDate to);

}