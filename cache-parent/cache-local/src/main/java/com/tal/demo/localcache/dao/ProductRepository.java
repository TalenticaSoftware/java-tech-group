package com.tal.demo.localcache.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.tal.demo.localcache.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
