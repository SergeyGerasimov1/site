package com.example.accounting.service.impl;

import com.example.accounting.model.WorkReport;
import com.example.accounting.model.WorkType;
import com.example.accounting.repository.WorkReportRepository;
import com.example.accounting.service.ReportService;
import com.example.accounting.service.WorkTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final WorkReportRepository workReportRepository;
    private final WorkTypeService workTypeService;

    @Override
    public List<WorkReport> getByExecutorAndDate(String executor, LocalDate from, LocalDate to) {
        if (from != null && to != null) {
            return workReportRepository.findByExecutorAndDateBetween(executor, from, to);
        }
        return workReportRepository.findByExecutor(executor);
    }

    public void save(WorkReport report, Long jobId) {
        WorkType workType = workTypeService.getById(jobId);
        report.setJob(workType);
        report.setSalary(report.getAmount() * report.getCoef() * report.getJob().getPrice());
        workReportRepository.save(report);
    }

    public WorkReport getById(Long id) {
        return workReportRepository.findById(id).orElseThrow();
    }

    public void updateReport(WorkReport updated) {
        workReportRepository.save(updated);
    }

    public void delete(Long id) {
        workReportRepository.deleteById(id);
    }

}
