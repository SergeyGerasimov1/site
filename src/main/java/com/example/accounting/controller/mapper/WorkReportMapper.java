package com.example.accounting.controller.mapper;

import com.example.accounting.controller.dto.WorkReportDTO;
import com.example.accounting.model.WorkReport;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WorkReportMapper {

    WorkReportDTO mapToDto(WorkReport workReport);

    WorkReport mapToEntity(WorkReportDTO workReport);

    List<WorkReportDTO> mapListToDTO(List<WorkReport> workReportList);

}
