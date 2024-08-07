package com.tal.cache.dao;

import com.tal.cache.model.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class ProductsDaoImpl {

  // Customer ID -> Product ID -> Products
  Map<Integer, Product> productDatabase = new ConcurrentHashMap<>();

  public List<Product> listProducts() {
    return productDatabase.values().stream().toList();
  }

  public Product getProduct(Integer productId) {
    return productDatabase.getOrDefault(productId, null);
  }

  public Product saveProduct(Product product) {
    productDatabase.put(product.getId(), product);
    return product;
  }

  public Product removeProduct(Integer productId) {
    return productDatabase.remove(productId);
  }

  public void clearAllProducts() {
    productDatabase.clear();
  }
}
