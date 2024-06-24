package com.talentica.virtual_thread.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/*
references :
    https://www.javacodegeeks.com/2013/05/java-8-definitive-guide-to-completablefuture.html
    https://spring.io/blog/2012/05/07/spring-mvc-3-2-preview-introducing-servlet-3-async-support
    https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-async.html

 */
@Service
public class DemoService {
    //@Autowired
    //Executor executor;
    Executor executor = new ForkJoinPool(2);

    public CompletableFuture<Double> execute() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(getStringFromNetwork(), executor);
        CompletableFuture<Integer> f2 = f1.thenApply(convertStringToIntegerFromNetwork());
        CompletableFuture<Double> f3 = f2.thenApply(convertIntegerToDoubleFromNetwork());
        return f3;
    }



    private Supplier<String> getStringFromNetwork(){
        return () -> {
            try {
                System.out.println("Get string is running in the thread: " + Thread.currentThread());
                //System.out.println("I am running in the thread: " + Thread.currentThread());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "42";
        };
    }

    private Function<String, Integer> convertStringToIntegerFromNetwork(){
        return (s) -> {
            try {
                System.out.println("converting to int, I am running in the thread: " + Thread.currentThread());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return Integer.parseInt(s);
        };
    }

    private Function<Integer, Double> convertIntegerToDoubleFromNetwork(){
        return (i) -> {
            try {
                System.out.println("converting to double, I am running in the thread: " + Thread.currentThread());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return i * 10.0;
        };
    }

    private Consumer<Double> printResult(){
        return (d) -> {
            System.out.println(d);
        };
    }
}
