//package com.talentica.services.department.service;
//
//import com.talentica.services.OrganizationAddEvent;
//import com.talentica.services.OrganizationRemoveEvent;
//import com.talentica.services.department.DepartmentDTO;
//import com.talentica.services.department.DepartmentExternalAPI;
//import com.talentica.services.department.DepartmentInternalAPI;
//import com.talentica.services.department.mapper.DepartmentMapper;
//import com.talentica.services.department.repository.DepartmentRepository;
//import com.talentica.services.employee.EmployeeDTO;
//import com.talentica.services.employee.service.EmployeeManagementService;
//import java.util.List;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.modulith.ApplicationModuleListener;
//import org.springframework.stereotype.Service;
//
//@Service
//@Slf4j
//public class DepartmentManagementService2 implements DepartmentInternalAPI, DepartmentExternalAPI {
//
//  private final DepartmentRepository repository;
//  private final EmployeeManagementService employeeManagementService;
//  private final DepartmentMapper mapper;
//
//  public DepartmentManagementService2(DepartmentRepository repository,
//      EmployeeManagementService employeeManagementService,
//      DepartmentMapper mapper
//  ) {
//    this.repository = repository;
//    this.employeeManagementService = employeeManagementService;
//    this.mapper = mapper;
//  }
//
//  @Override
//  public DepartmentDTO getDepartmentByIdWithEmployees(Long id) {
//    DepartmentDTO d = repository.findDTOById(id);
//    List<EmployeeDTO> dtos = employeeManagementService.getEmployeesByDepartmentId(id);
//    d.employees().addAll(dtos);
//    return d;
//  }
//
//  @ApplicationModuleListener
//  void onNewOrganizationEvent(OrganizationAddEvent event) throws Exception {
//    log.info("onNewOrganizationEvent(orgId={})", event.getId());
//
////    if (event != null) {
////      throw new Exception("failed due to some reason");
////    }
//    add(new DepartmentDTO(null, event.getId(), "HR"));
//    add(new DepartmentDTO(null, event.getId(), "Management"));
//  }
//
//  @ApplicationModuleListener
//  void onRemovedOrganizationEvent(OrganizationRemoveEvent event) {
//    log.info("onRemovedOrganizationEvent(orgId={})", event.getId());
//    repository.deleteByOrganizationId(event.getId());
//  }
//
//  @Override
//  public DepartmentDTO add(DepartmentDTO department) {
//    return mapper.departmentToDepartmentDTO(
//        repository.save(mapper.departmentDTOToDepartment(department))
//    );
//  }
//
//  @Override
//  public List<DepartmentDTO> getDepartmentsByOrganizationId(Long id) {
//    return repository.findByOrganizationId(id);
//  }
//
//  @Override
//  public List<DepartmentDTO> getDepartmentsByOrganizationIdWithEmployees(Long id) {
//    List<DepartmentDTO> departments = repository.findByOrganizationId(id);
//    for (DepartmentDTO dep : departments) {
//      dep.employees().addAll(employeeManagementService.getEmployeesByDepartmentId(dep.id()));
//    }
//    return departments;
//  }
//}
