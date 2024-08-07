package com.tal.cache.controller;

import com.tal.cache.dao.ProductsDaoImpl;
import com.tal.cache.model.Product;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
//@CacheConfig(cacheNames = "productsCache")
public class ProductService {

  public static final String PRODUCTS_CACHE = "productsCache";

  @Autowired
  ProductsDaoImpl productsDao;

  private final static Logger log = LoggerFactory.getLogger(com.tal.cache.controller.ProductService.class);

  public List<Product> listProducts() {
    log.info("Fetching list of products from data source");
    return productsDao.listProducts();
  }

  @Cacheable(cacheNames = PRODUCTS_CACHE , unless = "#result == null")
  public Product getProduct(Integer productId) {
    log.info("Fetching product {} from data source", productId);
    return productsDao.getProduct(productId);
  }

  @CacheEvict(cacheNames = PRODUCTS_CACHE)
  public Product removeProduct(Integer productId) {
    log.info("Removing product {} from data source", productId);
    return productsDao.removeProduct(productId);
  }

  @CacheEvict(cacheNames = PRODUCTS_CACHE,  allEntries = true)
  public void clearCache() {
    log.info("Removing products from cache");
  }

  @CachePut(cacheNames = PRODUCTS_CACHE, key = "#product.id", condition ="#product.customerId != '3'")
  public Product updateProduct(Product product) {
    log.info("Updating product [{}] to data source", product.getId());
    return productsDao.saveProduct(product);
  }

  @Cacheable(cacheNames = PRODUCTS_CACHE, key = "#product.id", condition = "#product.customerId != '3'")
  public Product addProduct(Product product) {
    log.info("Adding product [{}] to data source", product.getId());
    return productsDao.saveProduct(product);
  }

  @CacheEvict(cacheNames = PRODUCTS_CACHE,  allEntries = true)
  public void clearAllProducts() {
    log.info("Removing all products from data source");
      productsDao.clearAllProducts();
  }

  //below methods are only for demo annotations

  // Synchronize the invocation of the underlying method if several threads are attempting to load a value for the same key.
  // This is effectively a hint and the chosen cache provider might not actually support it in a synchronized fashion.
  @Cacheable(cacheNames = PRODUCTS_CACHE, sync = true)
  public Product getProductSync(Integer productId) {
    log.info("Fetching product {} from data source sync", productId);
    return productsDao.getProduct(productId);
  }

  @Caching(cacheable = {
    @Cacheable(cacheNames = PRODUCTS_CACHE, key = "#product.id"),
    @Cacheable(cacheNames = "customerCache", key = "#product.customerId")
  })
  public void addInMultipleCaches(Product product) {
    log.info("Adding product in multiple caches [{}] and then to data source", product.getId());
    productsDao.saveProduct(product);
  }

  @Cacheable(cacheNames = PRODUCTS_CACHE, keyGenerator = "customCacheKeyGenerator")
  public Product addProductWithCustomCacheKey(Product product) {
    log.info("Adding product [{}] to data source using custom cache key generator", product.getId());
    return productsDao.saveProduct(product);
  }

  @CachePut(cacheNames = PRODUCTS_CACHE, key = "#product.id", unless ="#result.customerId != '3'")
  public Product saveProduct(Product product) {
    log.info("saving product [{}] to data source", product.getId());
    return productsDao.saveProduct(product);
  }

  //This will skip cache due to spring self invocation issue.
  public Product getProductWithCacheAnnotation(Integer id) {
    return this.getProduct(id);
  }
}
