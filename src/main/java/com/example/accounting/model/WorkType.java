package com.example.accounting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WorkType {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double price;
    private String unit;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "sectionId")
    private WorkSection section;
}