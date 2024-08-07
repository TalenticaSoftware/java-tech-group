package com.tal.cache.controller;

import com.tal.cache.dao.ProductsDaoImpl;
import com.tal.cache.model.Product;
import java.util.List;

import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheRemoveAll;
import javax.cache.annotation.CacheValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheResult;
import javax.cache.annotation.CachePut;

@Service
@CacheDefaults(cacheName = "productsCache")
public class ProductService {

  public static final String PRODUCTS_CACHE = "productsCache";

  @Autowired
  ProductsDaoImpl productsDao;

  private final static Logger log = LoggerFactory.getLogger(com.tal.cache.controller.ProductService.class);

  public List<Product> listProducts() {
    log.info("Fetching list of products from data source");
    return productsDao.listProducts();
  }

  @CacheResult
  public Product getProduct(Integer productId) {
    log.info("Fetching product {} from data source", productId);
    return productsDao.getProduct(productId);
  }

  @CacheResult
  public Product addProduct(@CacheKey Integer productId, @CacheValue Product product) {
    log.info("Adding product [{}] to data source", productId);
    return productsDao.saveProduct(product);
  }

  @CachePut
  public Product updateProduct(@CacheKey Integer productId, @CacheValue Product product) {
    log.info("Updating product [{}] to data source", productId);
    return productsDao.saveProduct(product);
  }

  @CacheRemove
  public Product removeProduct(Integer productId) {
    log.info("Removing product {} from data source", productId);
    return productsDao.removeProduct(productId);
  }

  @CacheRemoveAll
  public void clearCache() {
    log.info("Removing products from cache");
  }
}
