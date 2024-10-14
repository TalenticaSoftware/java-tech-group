package com.talentica.services.employee.service;

import com.talentica.services.OrganizationRemoveEvent;
import com.talentica.services.employee.EmployeeDTO;
import com.talentica.services.employee.EmployeeExternalAPI;
import com.talentica.services.employee.EmployeeInternalAPI;
import com.talentica.services.employee.mapper.EmployeeMapper;
import com.talentica.services.employee.model.Employee;
import com.talentica.services.employee.repository.EmployeeRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeManagementService implements EmployeeInternalAPI, EmployeeExternalAPI {

  private final EmployeeRepository repository;
  private final EmployeeMapper mapper;


  public EmployeeManagementService(EmployeeRepository repository,
      EmployeeMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public List<EmployeeDTO> getEmployeesByDepartmentId(Long departmentId) {
    return repository.findByDepartmentId(departmentId);
  }

  @Override
  public List<EmployeeDTO> getEmployeesByOrganizationId(Long id) {
    return repository.findByOrganizationId(id);
  }

  @Override
  @Transactional
  public EmployeeDTO add(EmployeeDTO employee) {
    Employee emp = mapper.employeeDTOToEmployee(employee);
    return mapper.employeeToEmployeeDTO(repository.save(emp));
  }

  @ApplicationModuleListener
  void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
    log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
    repository.deleteByOrganizationId(event.getId());
  }

}
