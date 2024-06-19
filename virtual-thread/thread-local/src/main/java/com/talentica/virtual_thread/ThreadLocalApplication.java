package com.talentica.virtual_thread;


import java.util.ArrayList;
import java.util.List;

public class ThreadLocalApplication {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    static class  MyTask implements Runnable{
        int i;
        MyTask(int i ){
            this.i = i;
        }
        @Override
        public void run() {
            threadLocal.set(i);
            System.out.println("Thread " + Thread.currentThread().getName() + " has value: " + threadLocal.get());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = Thread.ofVirtual()
                    .name("Virtual thread " + i)
                    .unstarted(new MyTask(i));
            threads.add(t);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }


}


