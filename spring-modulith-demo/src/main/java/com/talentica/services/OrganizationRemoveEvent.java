package com.talentica.services;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationRemoveEvent {

  private Long id;

  public OrganizationRemoveEvent(Long id) {
    this.id = id;
  }

}
