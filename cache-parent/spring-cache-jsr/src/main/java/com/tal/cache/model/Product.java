package com.tal.cache.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product implements Serializable{
  private Integer id;
  private String name;
  private String customerId;

  public String getCustomerId() {
    return customerId;
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
