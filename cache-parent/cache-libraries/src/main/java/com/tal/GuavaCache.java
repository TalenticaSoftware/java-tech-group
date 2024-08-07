package com.tal;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCache {

    private LoadingCache<String, String> cache;

    public GuavaCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(100) // maximum 100 records can be cached
                .expireAfterAccess(30, TimeUnit.MINUTES) // cache will expire after 30 minutes of access
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return getDataFromDatabase(key);
                    }
                });
    }

    private String getDataFromDatabase(String key) {
        // Simulate a database call
        return "Data for " + key;
    }

    public String getData(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        GuavaCache guavaCache = new GuavaCache();
        System.out.println(guavaCache.getData("key1"));
        System.out.println(guavaCache.getData("key2"));
    }
}