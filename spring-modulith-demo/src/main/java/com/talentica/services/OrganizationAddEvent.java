package com.talentica.services;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrganizationAddEvent {

  public OrganizationAddEvent() {
  }

  private Long id;

  public OrganizationAddEvent(Long id) {
    this.id = id;
  }

}
