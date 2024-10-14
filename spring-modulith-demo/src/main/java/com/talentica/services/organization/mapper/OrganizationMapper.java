package com.talentica.services.organization.mapper;

import com.talentica.services.organization.OrganizationDTO;
import com.talentica.services.organization.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrganizationMapper {

  OrganizationDTO organizationToOrganizationDTO(Organization organization);

  Organization organizationDTOToOrganization(OrganizationDTO organizationDTO);
}
