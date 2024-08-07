package com.tal;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;

public class ProductCacheEntryAddListener implements CacheEntryCreatedListener<String, String> {

    @Override
    public void onCreated(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents)
            throws CacheEntryListenerException {
        cacheEntryEvents.forEach(cacheEvent -> {
            System.out.println("Added key : " + cacheEvent.getKey() + " Added value : " + cacheEvent.getValue());
        });
    }
}
