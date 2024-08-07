package com.tal.demo.standalone.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisCache<K, V> {

    @Autowired
    private RedisTemplate<K, V> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

    // Add or update a cache entry
    public void put(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
        logger.info("Cache put: {} -> {}", key, value);
    }

    // Retrieve a cache entry
    public V get(K key) {
        V value = redisTemplate.opsForValue().get(key);
        if (value != null) {
            logger.info("Cache hit: {} -> {}", key, value);
        } else {
            logger.warn("Cache miss: {}", key);
        }
        return value;
    }

    // Remove a cache entry
    public void remove(K key) {
        redisTemplate.delete(key);
        logger.info("Cache remove: {} -> {}", key);
    }

    // Check if the cache contains a key
    public boolean containsKey(K key) {
        boolean contains = null != redisTemplate.hasKey(key);
        logger.info("Cache contains key {}: {}", key, contains);
        return contains;
    }

    // Clear the cache
    public void clear() {
        logger.info("Cache cleared");
    }
}
