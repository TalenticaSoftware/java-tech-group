package com.tal.demo.standalone.service;


import com.tal.demo.standalone.cache.Cache;
import com.tal.demo.standalone.model.Product;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  Cache<String, Product> cache;

  private final static Logger log = LoggerFactory.getLogger(ProductService.class);

  public Product getProduct(String productId) {
    Product productFromCache = cache.get(productId);
    if (productFromCache != null) {
      return productFromCache;
    }

    Product productFromDataSource = getProductFromDataSource(productId);
    cache.put(productId, productFromDataSource);
    return productFromDataSource;
  }

  private Product getProductFromDataSource(String productId) {
    return Product.builder().id(productId).name("Product" + productId).build();
  }

  public Set<String> getProductIds() {
      return cache.getAllKeys("*");
  }
}
