package com.tal.demo.standalone.controller;

import com.tal.demo.standalone.model.Product;
import com.tal.demo.standalone.service.ProductService;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/{productId}")
  public Product getProduct(@PathVariable("productId") final String productId) {
    return productService.getProduct(productId);
  }

  @GetMapping
  public Set<String> getProductIds() {
    return productService.getProductIds();
  }
}
