package com.talentica.services.employee.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long organizationId;
  private Long departmentId;
  private String name;
  private int age;
  private String position;

  public Long getId() {
    return this.id;
  }

  public Long getOrganizationId() {
    return this.organizationId;
  }

  public Long getDepartmentId() {
    return this.departmentId;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public String getPosition() {
    return this.position;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setOrganizationId(Long organizationId) {
    this.organizationId = organizationId;
  }

  public void setDepartmentId(Long departmentId) {
    this.departmentId = departmentId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setPosition(String position) {
    this.position = position;
  }
}
