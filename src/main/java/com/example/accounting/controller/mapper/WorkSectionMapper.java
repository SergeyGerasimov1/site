package com.example.accounting.controller.mapper;

import com.example.accounting.controller.dto.WorkSectionDTO;
import com.example.accounting.model.WorkSection;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WorkSectionMapper {
    List<WorkSectionDTO> mapListToDTO(List<WorkSection> workSectionList);
}
