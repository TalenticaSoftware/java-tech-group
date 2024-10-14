package com.talentica.services;

public class OrganizationAddEvent {

  public OrganizationAddEvent() {
  }

  private Long id;

  public OrganizationAddEvent(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
