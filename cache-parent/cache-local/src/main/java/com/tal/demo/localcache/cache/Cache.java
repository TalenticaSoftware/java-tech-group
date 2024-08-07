package com.tal.demo.localcache.cache;

import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cache<K, V> {

    private final ConcurrentHashMap<K, V> map;
    private static final Logger logger = LoggerFactory.getLogger(Cache.class);

    public Cache() {
        this.map = new ConcurrentHashMap<>();
    }

    // Add or update a cache entry
    public void put(K key, V value) {
        map.put(key, value);
        logger.info("Cache put: {} -> {}", key, value);
    }

    // Retrieve a cache entry
    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            logger.info("Cache hit: {} -> {}", key, value);
        } else {
            logger.warn("Cache miss: {}", key);
        }
        return value;
    }

    // Remove a cache entry
    public V remove(K key) {
        V value = map.remove(key);
        logger.info("Cache remove: {} -> {}", key, value);
        return value;
    }

    // Check if the cache contains a key
    public boolean containsKey(K key) {
        boolean contains = map.containsKey(key);
        logger.info("Cache contains key {}: {}", key, contains);
        return contains;
    }

    // Clear the cache
    public void clear() {
        map.clear();
        logger.info("Cache cleared");
    }
}
