package com.tal.cache.controller;

import com.tal.cache.cache.CacheStatsHandler;
import com.tal.cache.model.Product;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  CacheStatsHandler cacheStatsHandler;

  @GetMapping
  public List<Product> listProducts() {
    return productService.listProducts();
  }

  @GetMapping("/{productId}")
  public Product getProduct(@PathVariable Integer productId) {
    return productService.getProduct(productId);
  }

  @PostMapping
  public Product addProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

  @PutMapping
  public Product updateProduct(@RequestBody Product product) {
    return productService.updateProduct(product);
  }

  @DeleteMapping("/clearAll")
  public void clearAll() {
    productService.clearAllProducts();
  }

  @DeleteMapping("/remove/{productId}")
  public Product removeProduct(@PathVariable Integer productId) {
    return productService.removeProduct(productId);
  }

  @DeleteMapping("/clearCache")
  public void clearCache() {
    productService.clearCache();
  }

  @PostMapping("/printStats")
  public Map<String, Object> printStatus() {
    return cacheStatsHandler.printCacheStats();
  }

  @PostMapping("/withCustomKey")
  public Product addProductWithCustomKey(@RequestBody Product product) {
    return productService.addProductWithCustomCacheKey(product);
  }
}
