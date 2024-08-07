package com.tal.demo.localcache.service;


import com.tal.demo.localcache.cache.Cache;
import com.tal.demo.localcache.dao.ProductRepository;
import com.tal.demo.localcache.model.Product;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  private static final Logger LOG = LoggerFactory.getLogger(ProductService.class);

  Cache<String, Product> cache = new Cache<>();

  public Product getProduct(String productId) {
    Product productFromCache = cache.get(productId);
    if (productFromCache != null) {
      LOG.info("Cache hit for product Id: {}", productId);
      return productFromCache;
    }

    LOG.info("Cache miss for product Id: {} , loading from data source..", productId);
    Optional<Product> productFromDataSource = getProductFromDataSource(productId);
    if(productFromDataSource.isPresent()) {
      cache.put(productId, productFromDataSource.get());
      return productFromDataSource.get();
    } else {
      LOG.info("Product not found in data source for product Id: {}", productId);
      return null;
    }
  }

  public void deleteProduct(String productId) {
    cache.remove(productId);
    productRepository.deleteById(productId);
  }

  private Optional<Product> getProductFromDataSource(String productId) {
      return productRepository.findById(productId);
  }

  public void saveProduct(Product product) {
    productRepository.save(product);
  }
}
