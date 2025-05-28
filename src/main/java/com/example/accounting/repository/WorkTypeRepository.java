package com.example.accounting.repository;

import com.example.accounting.model.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkTypeRepository extends JpaRepository<WorkType, Long> {
    List<WorkType> findBySectionId(Long sectionId);

}