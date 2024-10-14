package com.talentica.services.organization.service;

import com.talentica.services.OrganizationAddEvent;
import com.talentica.services.OrganizationRemoveEvent;
import com.talentica.services.department.DepartmentDTO;
import com.talentica.services.department.DepartmentInternalAPI;
import com.talentica.services.employee.EmployeeDTO;
import com.talentica.services.employee.EmployeeInternalAPI;
import com.talentica.services.organization.OrganizationDTO;
import com.talentica.services.organization.OrganizationExternalAPI;
import com.talentica.services.organization.mapper.OrganizationMapper;
import com.talentica.services.organization.repository.OrganizationRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrganizationManagementService implements OrganizationExternalAPI {

  private final ApplicationEventPublisher events;
  private final OrganizationRepository repository;
  private final DepartmentInternalAPI departmentInternalAPI;
  private final EmployeeInternalAPI employeeInternalAPI;
  private final OrganizationMapper mapper;

  public OrganizationManagementService(ApplicationEventPublisher events,
      OrganizationRepository repository,
      DepartmentInternalAPI departmentInternalAPI,
      EmployeeInternalAPI employeeInternalAPI,
      OrganizationMapper mapper) {
    this.events = events;
    this.repository = repository;
    this.departmentInternalAPI = departmentInternalAPI;
    this.employeeInternalAPI = employeeInternalAPI;
    this.mapper = mapper;
  }

  @Override
  public OrganizationDTO findByIdWithEmployees(Long id) {
    OrganizationDTO dto = repository.findDTOById(id);
    List<EmployeeDTO> dtos = employeeInternalAPI.getEmployeesByOrganizationId(id);
    dto.employees().addAll(dtos);
    return dto;
  }

  @Override
  public OrganizationDTO findByIdWithDepartments(Long id) {
    OrganizationDTO dto = repository.findDTOById(id);
    List<DepartmentDTO> dtos = departmentInternalAPI.getDepartmentsByOrganizationId(id);
    dto.departments().addAll(dtos);
    return dto;
  }

  @Override
  public OrganizationDTO findByIdWithDepartmentsAndEmployees(Long id) {
    OrganizationDTO dto = repository.findDTOById(id);
    List<DepartmentDTO> dtos = departmentInternalAPI.getDepartmentsByOrganizationIdWithEmployees(
        id);
    dto.departments().addAll(dtos);
    return dto;
  }

  @Override
  @Transactional
  public OrganizationDTO add(OrganizationDTO organization) {
    OrganizationDTO dto = mapper.organizationToOrganizationDTO(
        repository.save(mapper.organizationDTOToOrganization(organization))
    );
    events.publishEvent(new OrganizationAddEvent(dto.id()));
    return dto;
  }

  @Override
  @Transactional
  public void remove(Long id) {
    repository.deleteById(id);
    events.publishEvent(new OrganizationRemoveEvent(id));
  }

}
