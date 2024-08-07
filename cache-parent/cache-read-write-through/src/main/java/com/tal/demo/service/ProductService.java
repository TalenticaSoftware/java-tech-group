package com.tal.demo.service;

import com.sun.istack.NotNull;
import com.tal.demo.model.Product;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @Autowired
  JCacheCacheManager productCacheManager;

  private final static Logger log = LoggerFactory.getLogger(ProductService.class);

  public Product getProduct(String productId) {
    final ValueWrapper cachedValueWrapper = getProductCache().get(productId);
    return cachedValueWrapper == null? null : (Product) cachedValueWrapper.get();
  }

  public Product saveProduct(String productId) {
    Product product = new Product();
    product.setId(productId);
    product.setName(productId + Instant.now());

    getProductCache().put(productId, product);
    log.info("saving product with id  {} and name {}", product, product.getName());
    return product;
  }

  private @NotNull Cache getProductCache() {
    return productCacheManager.getCache("productCache");
  }
}
