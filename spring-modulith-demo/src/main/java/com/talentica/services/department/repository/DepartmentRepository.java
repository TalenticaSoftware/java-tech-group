package com.talentica.services.department.repository;

import com.talentica.services.department.DepartmentDTO;
import com.talentica.services.department.model.Department;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {

  @Query("""
      SELECT new com.talentica.services.department.DepartmentDTO(d.id, d.organizationId, d.name)
      FROM Department d
      WHERE d.id = :id
      """)
  DepartmentDTO findDTOById(Long id);

  @Query("""
      SELECT new com.talentica.services.department.DepartmentDTO(d.id, d.organizationId, d.name)
      FROM Department d
      WHERE d.organizationId = :organizationId
      """)
  List<DepartmentDTO> findByOrganizationId(Long organizationId);

  void deleteByOrganizationId(Long organizationId);
}
