package com.tal;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.AccessedExpiryPolicy;
import javax.cache.spi.CachingProvider;

public class Main {

  public static void main(String[] args) {

    // resolve a cache manager
    CachingProvider cachingProvider = Caching.getCachingProvider();
    CacheManager cacheManager = cachingProvider.getCacheManager();

    // configure the cache
    MutableConfiguration<String, String> config = new MutableConfiguration<String, String>()
        .setTypes(String.class, String.class)
        .setExpiryPolicyFactory(AccessedExpiryPolicy.factoryOf(Duration.ONE_HOUR))
        .setStatisticsEnabled(true);

    // Create and configure the cache entry created listener
    MutableCacheEntryListenerConfiguration<String, String> createdListenerConfig = new MutableCacheEntryListenerConfiguration<>(
        FactoryBuilder.factoryOf(ProductCacheEntryAddListener.class),
        null,
        false,
        true);

    // Create and configure the cache entry listener
    MutableCacheEntryListenerConfiguration<String, String> removedlistenerConfig = new MutableCacheEntryListenerConfiguration<>(
        FactoryBuilder.factoryOf(ProductCacheEntryRemovedListener.class),
        null,
        true,
        true);

    Cache<String, String> cache = cacheManager.createCache("productCache", config);
    cache.registerCacheEntryListener(createdListenerConfig);
    cache.registerCacheEntryListener(removedlistenerConfig);

    System.out.println("============= Cache config starts=============");
    System.out.println("cache provider  : " + cachingProvider.getClass().getName());
    System.out.println("cache manager : " + cacheManager.getClass().getName());
    System.out.println("cache type : " + cache.getClass().getName());
    System.out.println("============= Cache config ends=============");

    cache.put("pr1", "laptop");
    cache.put("pr2", "mouse");

    System.out.println("pr1 value" + cache.get("pr1"));
    System.out.println("pr2 value" + cache.get("pr2"));

    cache.remove("pr1");
  }
}