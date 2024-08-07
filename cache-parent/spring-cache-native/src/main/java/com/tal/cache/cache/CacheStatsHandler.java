package com.tal.cache.cache;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.cache.Cache.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.jcache.JCacheCache;
import org.springframework.stereotype.Component;

@Component
public class CacheStatsHandler {

  private final static Logger log = LoggerFactory.getLogger(CacheStatsHandler.class);

  @Autowired
  CacheManager cacheManager;

  public Map<String,Object> printCacheStats() {
    log.info("Cache names : {}", cacheManager.getCacheNames());

    int cacheSize = 0;
    Set<Object> keys = Collections.emptySet();

    final Cache productsCache = cacheManager.getCache("productsCache");
    log.info("Cache type : {}", productsCache.getClass().getName());


    if (productsCache != null) {


      if (productsCache instanceof ConcurrentMapCache internalCache) {
        cacheSize = internalCache.getNativeCache().size();
        keys = internalCache.getNativeCache().keySet();
        log.info("Cache size of concurrent map cache {} ", cacheSize);
        log.info("Cache entries of concurrent map cache {} ", keys);
      }

      else if (productsCache instanceof JCacheCache internalCache) {

        Iterator<Entry<Object, Object>> iterator = internalCache.getNativeCache().iterator();

        keys = StreamSupport.stream(((Iterable<Entry<Object, Object>>) () -> iterator).spliterator(), false)
                            .map(Entry::getKey)
                            .collect(Collectors.toSet());

        cacheSize = keys.size();                    
        log.info("Cache size of ehCache cache {} ", keys.size());
        log.info("Cache entries of ehCache cache {} ", keys);
      }
      // else if (productsCache instanceof CaffeineCache internalCache) {
      // log.info("Cache size of Caffeine cache {}
      // ",internalCache.getNativeCache().asMap().size());
      // log.info("Cache entries of Caffeine cache {}
      // ",internalCache.getNativeCache().asMap().keySet());
      // }
      else {
        throw new UnsupportedOperationException("Cache is not supported");
      }
    }
    return Map.of("cache size", cacheSize, "cache keys", keys);
  }
}
