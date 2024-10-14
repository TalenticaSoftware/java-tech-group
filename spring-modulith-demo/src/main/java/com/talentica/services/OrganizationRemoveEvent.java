package com.talentica.services;

public class OrganizationRemoveEvent {

  private Long id;

  public OrganizationRemoveEvent(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
