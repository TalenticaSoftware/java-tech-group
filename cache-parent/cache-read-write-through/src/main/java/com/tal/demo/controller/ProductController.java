package com.tal.demo.controller;


import com.tal.demo.model.Product;
import com.tal.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @PostMapping("/{productId}")
  public Product saveProduct(@PathVariable("productId") final String productId) {
    return productService.saveProduct(productId);
  }
}
