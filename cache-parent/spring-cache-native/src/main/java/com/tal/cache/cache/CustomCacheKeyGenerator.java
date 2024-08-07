package com.tal.cache.cache;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class CustomCacheKeyGenerator implements KeyGenerator {

  @Override
  public Object generate(Object target, Method method, Object... params) {
    String keyStr = target.getClass().getSimpleName() + "_" + method.getName() + "_" + Arrays.toString(params);
    return keyStr.hashCode();
  }   
}
