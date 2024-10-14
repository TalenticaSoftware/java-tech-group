package com.talentica.services.organization.repository;

import com.talentica.services.organization.OrganizationDTO;
import com.talentica.services.organization.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Long> {

  @Query("""
      SELECT new com.talentica.services.organization.OrganizationDTO(o.id, o.name, o.address)
      FROM Organization o
      WHERE o.id = :id
      """)
  OrganizationDTO findDTOById(Long id);
}
