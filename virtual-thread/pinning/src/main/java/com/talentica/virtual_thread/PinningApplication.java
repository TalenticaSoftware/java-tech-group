package com.talentica.virtual_thread;


import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
/*
JVM arguments:

    -Djdk.tracePinnedThreads=short/full

 */
public class PinningApplication {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var lock = new Object();

        Runnable task1 =
                () -> {
                    System.out.println(Thread.currentThread());
                    synchronized (lock) {
                        counter++;
                        sleepFor(1, ChronoUnit.MICROS);
                    }
                    System.out.println(Thread.currentThread());
                    synchronized (lock) {
                        counter++;
                        sleepFor(1, ChronoUnit.MICROS);
                    }
                    System.out.println(Thread.currentThread());
                    synchronized (lock) {
                        counter++;
                        sleepFor(1, ChronoUnit.MICROS);
                    }
                    System.out.println(Thread.currentThread());
                };
        Runnable task2 =
                () -> {
                    synchronized (lock) {
                        counter++;
                        sleepFor(1, ChronoUnit.MICROS);
                    }
                    synchronized (lock) {
                        counter++;
                        sleepFor(1, ChronoUnit.MICROS);
                    }
                    synchronized (lock) {
                        counter++;
                        sleepFor(1, ChronoUnit.MICROS);
                    }
                };

        int N_THREADS = 3000;

        var threads = new ArrayList<Thread>();

        for (int index = 0; index < N_THREADS; index++) {
            var thread =
                    index == 0 ?
                            Thread.ofVirtual().unstarted(task1) :
                            Thread.ofVirtual().unstarted(task2);
            threads.add(thread);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("# threads = " + N_THREADS);
        System.out.println("counter   = " + counter);
    }

    private static void sleepFor(int amount, ChronoUnit unit) {
        try {
            Thread.sleep(Duration.of(amount, unit));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}


