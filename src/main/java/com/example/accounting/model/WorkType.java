package com.example.accounting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkType {

    @Id
    @GeneratedValue
    private Long id;

    private String code;
    private String name;
    private String unit;
    private double price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "section_id")
    private WorkSection section;
}
