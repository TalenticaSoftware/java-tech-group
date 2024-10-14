package com.talentica.services.department.mapper;

import com.talentica.services.department.DepartmentDTO;
import com.talentica.services.department.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

  DepartmentDTO departmentToDepartmentDTO(Department department);

  Department departmentDTOToDepartment(DepartmentDTO departmentDTO);
}
