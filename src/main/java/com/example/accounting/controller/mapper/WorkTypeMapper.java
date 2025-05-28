package com.example.accounting.controller.mapper;

import com.example.accounting.controller.dto.WorkTypeDTO;
import com.example.accounting.model.WorkType;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WorkTypeMapper {
    List<WorkTypeDTO> mapListToDTO(List<WorkType> workTypesList);
}
