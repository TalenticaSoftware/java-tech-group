package com.tal.demo.cache;

import com.tal.demo.BeanUtil;
import com.tal.demo.model.Product;
import com.tal.demo.repo.ProductRepository;
import com.tal.demo.service.ProductService;

import org.ehcache.spi.loaderwriter.CacheLoaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCacheLoaderWriter implements CacheLoaderWriter<String, Product> {

  private final static Logger LOG = LoggerFactory.getLogger(ProductService.class);


  @Override
  public Product load(String id) throws Exception {
    LOG.info("CacheLoaderWriter : Loading product {} from data source", id);
    return getProductRepository().findById(id).orElse(null);
  }

  @Override
  public void write(String id, Product product) {
    LOG.info("CacheLoaderWriter : Saving product {} to data source", product);
    getProductRepository().save(product);
  }

  @Override
  public void delete(String id) {
    getProductRepository().deleteById(id);
  }

  private static ProductRepository getProductRepository() {
    return BeanUtil.getBean(ProductRepository.class);
  }
}
