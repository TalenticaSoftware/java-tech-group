package com.talentica.services.gateway;

import com.talentica.services.department.DepartmentDTO;
import com.talentica.services.department.DepartmentExternalAPI;
import com.talentica.services.employee.EmployeeDTO;
import com.talentica.services.employee.EmployeeExternalAPI;
import com.talentica.services.organization.OrganizationDTO;
import com.talentica.services.organization.OrganizationExternalAPI;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GatewayManagementService {

  private final DepartmentExternalAPI departmentExternalAPI;
  private final EmployeeExternalAPI employeeExternalAPI;
  private final OrganizationExternalAPI organizationExternalAPI;

  public GatewayManagementService(DepartmentExternalAPI departmentExternalAPI,
      EmployeeExternalAPI employeeExternalAPI,
      OrganizationExternalAPI organizationExternalAPI) {
    this.departmentExternalAPI = departmentExternalAPI;
    this.employeeExternalAPI = employeeExternalAPI;
    this.organizationExternalAPI = organizationExternalAPI;
  }


  @GetMapping("/organizations/{id}/with-departments")
  public OrganizationDTO apiOrganizationWithDepartments(@PathVariable("id") Long id) {
    return organizationExternalAPI.findByIdWithDepartments(id);
  }

  @GetMapping("/organizations/{id}/with-departments-and-employees")
  public OrganizationDTO apiOrganizationWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
    return organizationExternalAPI.findByIdWithDepartmentsAndEmployees(id);
  }

  @PostMapping("/organizations")
  public OrganizationDTO apiAddOrganization(@RequestBody OrganizationDTO o) {
    return organizationExternalAPI.add(o);
  }

  @PostMapping("/employees")
  public EmployeeDTO apiAddEmployee(@RequestBody EmployeeDTO employee) {
    return employeeExternalAPI.add(employee);
  }

  @GetMapping("/employee/report/{id}/")
  public String apiAddEmployee(@PathVariable Long id) {
    return employeeExternalAPI.getEmployeeReport(id);
  }

  @GetMapping("/departments/{id}/with-employees")
  public DepartmentDTO apiDepartmentWithEmployees(@PathVariable("id") Long id) {
    return departmentExternalAPI.getDepartmentByIdWithEmployees(id);
  }

  @PostMapping("/departments")
  public DepartmentDTO apiAddDepartment(@RequestBody DepartmentDTO department) {
    return departmentExternalAPI.add(department);
  }
}
