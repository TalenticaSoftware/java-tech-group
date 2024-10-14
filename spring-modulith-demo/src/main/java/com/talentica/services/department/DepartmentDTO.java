package com.talentica.services.department;

import com.talentica.services.employee.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;

public record DepartmentDTO(Long id,
                            Long organizationId,
                            String name,
                            List<EmployeeDTO> employees) {

  public DepartmentDTO(Long id, Long organizationId, String name) {
    this(id, organizationId, name, new ArrayList<>());
  }
}
