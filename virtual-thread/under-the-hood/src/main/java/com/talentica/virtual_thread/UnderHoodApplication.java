package com.talentica.virtual_thread;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
  Look at java.lang.VirtualThread's method : createDefaultScheduler()

  String parallelismValue = System.getProperty("jdk.virtualThreadScheduler.parallelism");
  String maxPoolSizeValue = System.getProperty("jdk.virtualThreadScheduler.maxPoolSize");
  String minRunnableValue = System.getProperty("jdk.virtualThreadScheduler.minRunnable");

 */

public class UnderHoodApplication {

    public static final Pattern POOL_PATTERN =
            Pattern.compile("ForkJoinPool-[\\d?]");
    public static final Pattern WORKER_PATTERN =
            Pattern.compile("worker-[\\d?]+");

    public static void main(String[] args) {
        System.out.println("# cores = " + Runtime.getRuntime().availableProcessors());

        Set<String> poolNames = ConcurrentHashMap.newKeySet();
        Set<String> pThreadNames = ConcurrentHashMap.newKeySet();


        Runnable task = () -> {
            String poolName = readPoolName(Thread.currentThread().toString());
            String workerName = readWorkerName(Thread.currentThread().toString());
            poolNames.add(poolName);
            pThreadNames.add(workerName);
        };
        int n = 100_000;
        try(var es = Executors.newVirtualThreadPerTaskExecutor()){
            for (int i = 0; i < n; i++) {
                es.submit(task);
            }
        }

        System.out.println("### Pools");
        System.out.println(poolNames);
        System.out.println("### Platform threads: " + pThreadNames.size());
    }

    private static String readWorkerName(String name) {
        Matcher workerMatcher = WORKER_PATTERN.matcher(name);
        if (workerMatcher.find()) {
            return workerMatcher.group();
        }
        return "not found";
    }

    private static String readPoolName(String name) {
        Matcher poolMatcher = POOL_PATTERN.matcher(name);
        if (poolMatcher.find()) {
            return poolMatcher.group();
        }
        return "pool not found";
    }
}


