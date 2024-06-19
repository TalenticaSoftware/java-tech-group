package com.talentica.virtual_thread;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

public class PatternExecutorsApplication {

    public static void main(String[] args) {
        Set<String> set = ConcurrentHashMap.newKeySet();
        Runnable task = () -> set.add(Thread.currentThread().toString());

        int n = 100;

        //try (var executor = Executors.newFixedThreadPool(10)) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < n; i++) {
                executor.submit(task);
            }
        }

        System.out.println(" # of threads in the set: " + set.size());


    }
}
