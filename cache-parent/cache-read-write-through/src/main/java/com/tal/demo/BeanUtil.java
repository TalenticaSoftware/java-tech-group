package com.tal.demo;

import org.springframework.context.ApplicationContext;

public class BeanUtil {
    private static ApplicationContext context;

    // Static method to set the ApplicationContext
    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    // Static method to get a bean by name
    public static <T> T getBean(String beanName, Class<T> clazz) {
        if (context == null) {
            throw new IllegalStateException("ApplicationContext not set.");
        }
        return context.getBean(beanName, clazz);
    }

    // Static method to get a bean by type
    public static <T> T getBean(Class<T> clazz) {
        if (context == null) {
            throw new IllegalStateException("ApplicationContext not set.");
        }
        return context.getBean(clazz);
    }
}
