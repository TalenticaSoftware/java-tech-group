package com.tal.demo.standalone.service;


import com.tal.demo.standalone.cache.RedisCache;
import com.tal.demo.standalone.dao.ProductRepository;
import com.tal.demo.standalone.model.Product;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  RedisCache<String, Product> redisCache;

  @Autowired
  ProductRepository productRepository;  

  private final static Logger LOG = LoggerFactory.getLogger(ProductService.class);

  public Product getProduct(String productId) {
    Product productFromCache = redisCache.get(productId);
    if (productFromCache != null) {
      LOG.info("Cache hit for product Id: {}", productId);
      return productFromCache;
    }

    LOG.info("Cache miss for product Id: {} , loading from data source..", productId);
    Optional<Product> productFromDataSource = getProductFromDataSource(productId);
    if(productFromDataSource.isPresent()) {
      redisCache.put(productId, productFromDataSource.get());
      return productFromDataSource.get();
    } else {
      LOG.info("Product not found in data source for product Id: {}", productId);
      return null;
    }
  }

  public void deleteProduct(String productId) {
    redisCache.remove(productId);
    productRepository.deleteById(productId);
  }

  private Optional<Product> getProductFromDataSource(String productId) {
      return productRepository.findById(productId);
  }

  public void saveProduct(Product product) {
    redisCache.put(product.getId(), product);
    productRepository.save(product);
  }
}
