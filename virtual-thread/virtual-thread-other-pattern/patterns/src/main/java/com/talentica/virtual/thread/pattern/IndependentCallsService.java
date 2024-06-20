package com.talentica.virtual.thread.pattern;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.concurrent.*;
import java.util.function.Supplier;

@Service
public class IndependentCallsService {

    private RestClient restClient;
    @Value("${app.baseUrl}")
    private String baseUrl;

    @PostConstruct
    void init() {
        restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    /*
        For both virtual thread and platform thread :
        The following code is:
            - not good for performance
            - not good for high-throughput.
            - Good for readability, debugging and testing.
     */
    public MessageWithDelay fanoutWrong() {
        long startTime = System.currentTimeMillis();
        String message1 = restClient.get()
                .uri(b -> b
                        .queryParam("error", false)
                        .queryParam("returnMessage", "hello")
                        .pathSegment("mock").pathSegment("{time}").build(2000))
                .retrieve()
                .body(String.class);

        String message2 = restClient.get()
                .uri(b -> b
                        .queryParam("error", false)
                        .queryParam("returnMessage", "world")
                        .pathSegment("mock").pathSegment("{time}").build(2000))
                .retrieve()
                .body(String.class);
        long endTime = System.currentTimeMillis();
        Duration duration = Duration.ofSeconds(endTime - startTime);
        return new MessageWithDelay(message1 + " " + message2, duration.getSeconds());
    }

    /*
         For platform threads :
         The following code is
         - good for performance
         - good for readability, debugging and testing.
         - Not good for high-throughput.
         Reason:
            Everytime a task is submitted new thread is created or taken from the pool.
            In case of platform threads, we have limited number of threads and we won't able to achieve high-throughput.
     */
    public MessageWithDelay fanoutOldNotCorrectWay() {
        String result = "";
        long startTime = System.currentTimeMillis();
        Callable<String> task1 = () -> {
            //System.out.println("Thread 1: " + Thread.currentThread().getName());
            String message1 = restClient.get()
                    .uri(b -> b
                            .queryParam("error", false)
                            .queryParam("returnMessage", "hello")
                            .pathSegment("mock").pathSegment("{time}").build(2000))
                    .retrieve()
                    .body(String.class);
            return message1;
        };
        Callable<String> task2 = () -> {
            //System.out.println("Thread 1: " + Thread.currentThread().getName());
            String message2 = restClient.get()
                    .uri(b -> b
                            .queryParam("error", false)
                            .queryParam("returnMessage", "world")
                            .pathSegment("mock").pathSegment("{time}").build(2000))
                    .retrieve()
                    .body(String.class);
            return message2;
        };
        //Here we are using platform threads to submit the task.
        try (var es = Executors.newThreadPerTaskExecutor(Executors.defaultThreadFactory())) {
            Future f1 = es.submit(task1);
            Future f2 = es.submit(task2);
            result = f1.get() + " " + f2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        Duration duration = Duration.ofSeconds(endTime - startTime);
        return new MessageWithDelay(result, duration.getSeconds());
    }

    /*
         For platform threads :
             The following code is
             - good for performance
             - still good for high-throughput (we are still limited by the number of threads)
             - Not good for readability, debugging and testing.
         Reason:
            The complex logic written using completable future is not easy to understand, debug and test.
            The completable chain once assigned to the thread, and if the chain is waiting for the response, the thread is blocked.
     */
    public MessageWithDelay fanoutOldCorrectWay() {
        ExecutorService executor = new ForkJoinPool(2);
        String result = "";
        long startTime = System.currentTimeMillis();
        Supplier<String> task1 = () -> {
            String message1 = restClient.get()
                    .uri(b -> b
                            .queryParam("error", false)
                            .queryParam("returnMessage", "hello")
                            .pathSegment("mock").pathSegment("{time}").build(2000))
                    .retrieve()
                    .body(String.class);
            return message1;
        };
        Supplier<String> task2 = () -> {
            String message2 = restClient.get()
                    .uri(b -> b
                            .queryParam("error", false)
                            .queryParam("returnMessage", "world")
                            .pathSegment("mock").pathSegment("{time}").build(2000))
                    .retrieve()
                    .body(String.class);
            return message2;
        };
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(task1, executor);
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(task2, executor);
        try {
            result = f1.get() + " " + f2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        Duration duration = Duration.ofSeconds(endTime - startTime);
        return new MessageWithDelay(result, duration.getSeconds());
    }

    /*
        For virtual threads :
            The following code is
             - good for performance
             - good for high-throughput
             - good for readability, debugging and testing.
     */
    public MessageWithDelay fanoutRight() {
        String result = "";
        long startTime = System.currentTimeMillis();
        Callable<String> task1 = () -> restClient.get()
                .uri(b -> b
                        .queryParam("error", false)
                        .queryParam("returnMessage", "hello")
                        .pathSegment("mock").pathSegment("{time}").build(2000))
                .retrieve()
                .body(String.class);
        Callable<String> task2 = () -> restClient.get()
                .uri(b -> b
                        .queryParam("error", false)
                        .queryParam("returnMessage", "world")
                        .pathSegment("mock").pathSegment("{time}").build(2000))
                .retrieve()
                .body(String.class);
        //Here we are using virtual threads to submit the task.
        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {
            Future f1 = es.submit(task1);
            Future f2 = es.submit(task2);
            result = f1.get() + " " + f2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        Duration duration = Duration.ofSeconds(endTime - startTime);
        return new MessageWithDelay(result, duration.getSeconds());
    }

}
