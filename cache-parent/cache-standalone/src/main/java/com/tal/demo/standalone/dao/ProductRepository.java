package com.tal.demo.standalone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tal.demo.standalone.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
}
