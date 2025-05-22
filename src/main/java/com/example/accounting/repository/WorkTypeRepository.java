package com.example.accounting.repository;

import com.example.accounting.model.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkTypeRepository extends JpaRepository<WorkType, Long>  {
    List<WorkType> findBySectionId(Long sectionId);
}
