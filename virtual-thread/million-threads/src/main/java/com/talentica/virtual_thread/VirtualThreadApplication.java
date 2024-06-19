package com.talentica.virtual_thread;

public class VirtualThreadApplication {
    public static void main(String[] args) {
        for (int i = 0; i < 1_000_000; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {

                    throw new RuntimeException(e);
                }
            });
            System.out.println("Thread created : " + (i + 1));
        }
    }
}
