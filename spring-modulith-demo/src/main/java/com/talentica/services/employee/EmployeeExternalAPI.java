package com.talentica.services.employee;

public interface EmployeeExternalAPI {

  EmployeeDTO add(EmployeeDTO employee);

  String getEmployeeReport(Long id);
}
