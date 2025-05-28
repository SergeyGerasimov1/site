package com.example.accounting.repository;

import com.example.accounting.model.WorkSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkSectionRepository extends JpaRepository<WorkSection, Long> {
}
