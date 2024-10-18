package com.talentica.services.employee.service;

import com.talentica.services.OrganizationRemoveEvent;
import com.talentica.services.common.util.PdfGenerator;
import com.talentica.services.employee.EmployeeDTO;
import com.talentica.services.employee.EmployeeExternalAPI;
import com.talentica.services.employee.EmployeeInternalAPI;
import com.talentica.services.employee.mapper.EmployeeMapper;
import com.talentica.services.employee.model.Employee;
import com.talentica.services.employee.repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class EmployeeManagementService implements EmployeeInternalAPI, EmployeeExternalAPI {

  private final EmployeeRepository repository;
  private final EmployeeMapper mapper;

  private PdfGenerator pdfGenerator;

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

  @Override
  public String getEmployeeReport(Long id) {
    Optional<Employee> employee = repository.findById(id);

    AtomicReference<String> report = new AtomicReference<>(" ");

    employee.ifPresent(value -> {
      StringBuilder builder = new StringBuilder()
          .append("***** Employee Report *****\n")
          .append("===========================\n")
          .append("Name     : ").append(value.getName()).append("\n")
          .append("Age      : ").append(value.getAge()).append("\n")
          .append("Position : ").append(value.getPosition()).append("\n")
          .append("===========================\n")
          .append("Generated on: ").append(LocalDate.now()).append("\n");

      report.set(PdfGenerator.generatePdf(builder.toString()));
    });

    return report.get();
  }


}
