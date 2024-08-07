package com.tal.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableCaching
public class CacheWriteThroughApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(CacheWriteThroughApplication.class, args);
		BeanUtil.setApplicationContext(applicationContext);
	}
}
