package com.talentica.services.employee.repository;

import com.talentica.services.employee.EmployeeDTO;
import com.talentica.services.employee.model.Employee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

  List<EmployeeDTO> findByDepartmentId(Long departmentId);

  List<EmployeeDTO> findByOrganizationId(Long organizationId);

  void deleteByOrganizationId(Long organizationId);

}
