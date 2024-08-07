package com.tal.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * The main class for the Spring Cache Native application.
 */
@SpringBootApplication
@EnableCaching
public class SpringCacheNativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheNativeApplication.class, args);
	}

	// @Bean
	// public CacheManager cacheManager() {
	// 	final CacheManagerBuilder<PersistentCacheManager> clusteredCacheManagerBuilder =
	// 					CacheManagerBuilder.newCacheManagerBuilder()
	// 									.with(ClusteringServiceConfigurationBuilder.cluster(URI.create("terracotta://localhost:9410/spring-native-cache"))
	// 													.autoCreate());
	// 	return (CacheManager) clusteredCacheManagerBuilder.build(true);
	// }

	// @Bean
    // public Caffeine<Object, Object> caffeine() {
    //     return Caffeine.newBuilder()
    //             .expireAfterWrite(10, TimeUnit.MINUTES)
    //             .maximumSize(1000);
    // }

    // @Bean
    // public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
    //     CaffeineCacheManager cacheManager = new CaffeineCacheManager();
    //     cacheManager.setCaffeine(caffeine);
    //     return cacheManager;
    // }
}
