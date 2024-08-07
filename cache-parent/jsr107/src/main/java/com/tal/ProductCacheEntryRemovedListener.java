package com.tal;

import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;

public class ProductCacheEntryRemovedListener implements CacheEntryRemovedListener<String, String> {

  @Override
  public void onRemoved(Iterable<CacheEntryEvent<? extends String, ? extends String>> cacheEntryEvents) throws CacheEntryListenerException {
    cacheEntryEvents.forEach(cacheEvent -> {
      System.out.println("Removed key : " + cacheEvent.getKey() + " Removed value : " + cacheEvent.getValue());
    });
  }
}
