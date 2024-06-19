package com.talentica.virtual_thread;

public class ThreadApplication {

    public static void main(String[] args) {
        for(int i = 0 ; i< 1_000_000; i++){
            new Thread(()-> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                    throw new RuntimeException(e);
                }
            }).start();
            System.out.println("Thread created : " + i);
        }
    }

}
