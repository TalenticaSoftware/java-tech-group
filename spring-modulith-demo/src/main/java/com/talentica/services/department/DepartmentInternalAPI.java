package com.talentica.services.department;

import com.talentica.services.organization.OrganizationDTO;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentInternalAPI {

  List<DepartmentDTO> getDepartmentsByOrganizationId(Long id);

  List<DepartmentDTO> getDepartmentsByOrganizationIdWithEmployees(Long id);

  /*
    department having dependency of organization
    organization is having dependency department internal api
   */
//  default List<DepartmentDTO> getAllDepartmentsFromOgranization(OrganizationDTO organization) {
//    return new ArrayList<>();
//  }
}
