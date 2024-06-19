package com.talentica.virtual.thread.old_version.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public void execute() {
            System.out.println("I am running in the thread: " + Thread.currentThread());
            System.out.println("I am going to read a file");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("I am done reading a file");
            System.out.println("After reading, I am running in the thread: " + Thread.currentThread());
    }
}
