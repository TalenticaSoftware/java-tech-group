package com.talentica.virtual_thread;

import java.util.ArrayList;
import java.util.List;

public class BlockingVirtualThreadApplication {

    public static void main(String[] args) throws InterruptedException {
        Runnable printableTask = () -> {
            System.out.println(Thread.currentThread()+ " : working...");
            sleep(10);
            System.out.println(Thread.currentThread() + " : working...");
            sleep(10);
            System.out.println(Thread.currentThread() + " : working...");
            sleep(10);
            System.out.println(Thread.currentThread() + " : working...");
            sleep(10);
            System.out.println(Thread.currentThread() + " : working...");
            sleep(10);
            System.out.println(Thread.currentThread() + " : working...");
        };

        Runnable waitingTask = () -> {
            sleep(10);
            sleep(10);
            sleep(10);
            sleep(10);
            sleep(10);
        };

        int numberOfThreads = 12;
        List<Thread> threads = new ArrayList<>();
        Thread thread = Thread.ofVirtual().unstarted(printableTask);
        threads.add(thread);

        for(int i = 0; i < numberOfThreads; i++){
            threads.add(Thread.ofVirtual().unstarted((waitingTask)));
        }

        for(Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

