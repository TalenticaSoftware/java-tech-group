package com.tal;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.checkerframework.checker.nullness.qual.Nullable;

public class CaffeineCache {

    private final AsyncCache<String, String> cache;

    public CaffeineCache() {
        cache = Caffeine.newBuilder()
                .maximumSize(100) // maximum 100 records can be cached
                .expireAfterWrite(10, TimeUnit.MINUTES) // cache will expire 10 minutes after the entry is created or replaced
                .buildAsync(new CustomCacheLoader());
    }

    public @Nullable String getData(String key) throws InterruptedException, ExecutionException {
        return cache.getIfPresent(key).get();
    }

    public static void main(String[] args) {
        CaffeineCache caffeineCache = new CaffeineCache();
        try {
            System.out.println(caffeineCache.getData("key1"));
            System.out.println(caffeineCache.getData("key2"));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class CustomCacheLoader implements CacheLoader<String, String> {

        @Override
        public CompletableFuture<? extends String> asyncReload(String key, String oldValue, Executor executor) throws Exception {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return oldValue + 1;
            });
        }

        @Override
        public String load(String key) throws Exception {
            return "100";
        }
    }
}