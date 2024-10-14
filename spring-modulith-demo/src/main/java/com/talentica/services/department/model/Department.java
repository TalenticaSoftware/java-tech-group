package com.talentica.services.department.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
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

}
