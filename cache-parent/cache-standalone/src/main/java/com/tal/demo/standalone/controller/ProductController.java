package com.tal.demo.standalone.controller;

import com.tal.demo.standalone.model.Product;
import com.tal.demo.standalone.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/{productId}")
  public Product getProduct(@PathVariable("productId") final String productId) {
    return productService.getProduct(productId);
  }


  @DeleteMapping("/{productId}")
  public String deleteProduct(@PathVariable("productId") final String productId) {
    productService.deleteProduct(productId);
    return "productId: " + productId + " deleted successfully";
  }

  @PostMapping
  public Product saveProduct(@RequestBody Product product) {
    productService.saveProduct(product);
    return product;
  }
}
