package com.talentica.services.department.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long organizationId;
  private String name;

  public Department() {
  }

  public Department(Long organizationId, String name) {
    this.organizationId = organizationId;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public Long getOrganizationId() {
    return this.organizationId;
  }

  public String getName() {
    return this.name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  public void setName(String name) {
    this.name = name;
  }
}
