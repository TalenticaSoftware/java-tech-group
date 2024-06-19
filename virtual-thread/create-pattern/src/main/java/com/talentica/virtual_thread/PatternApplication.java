package com.talentica.virtual_thread;


public class PatternApplication {

    public static void main(String[] args) throws InterruptedException {

        Runnable task = () -> {
            System.out.println("I am running in the thread: " +
                    Thread.currentThread().getName());
            System.out.println("Am I daemon thread !" + Thread.currentThread().isDaemon() );
            System.out.println();
        };
        //Old thread creation pattern for creating platform thread
        Thread thread = new Thread(task);
        thread.run();
        thread.join();

        //New way of creating platform thread
        Thread thread1 = Thread.ofPlatform()
                .name("Platform thread 1")
                .daemon(true)
                .unstarted(task);
        thread1.start();
        thread1.join();

        //creating virtual thread
        Thread thread2 = Thread.ofVirtual()
                .name("Virtual thread 2")
                //.daemon(false) no way to set daemon for virtual thread. It is always daemon.
                .unstarted(task);
        thread2.start();
        thread2.join();

        Thread thread3 = Thread.startVirtualThread(task);
        thread3.join();



    }


}


