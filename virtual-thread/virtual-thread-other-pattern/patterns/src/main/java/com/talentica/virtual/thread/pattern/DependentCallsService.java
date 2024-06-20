package com.talentica.virtual.thread.pattern;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

@Service
public class DependentCallsService {

    private RestClient restClient;
    @Value("${app.baseUrl}")
    private String baseUrl;

    @PostConstruct
    void init() {
        restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    /*
    In case of platform thread below code is a right way to do it
    but difficult to read, debug and test.
     */
    public MessageWithDelay asyncNonBlockingStyle() {
        //use completable future to first call the first api and then call the second api, passing the response from first api call
        long startTime = System.currentTimeMillis();
        CompletableFuture<String> future1 =
                CompletableFuture.supplyAsync(() -> restClient.get()
                        .uri(b -> b
                                .queryParam("error", false)
                                .queryParam("returnMessage", "hello")
                                .pathSegment("mock").pathSegment("{time}").build(1000))
                        .retrieve()
                        .body(String.class));
        CompletableFuture<String> future2 =
                future1.thenCompose(
                        result1 -> CompletableFuture.supplyAsync(() -> restClient.get()
                                .uri(b -> b
                                        .queryParam("error", false)
                                        .queryParam("returnMessage", result1)
                                        .pathSegment("mock").pathSegment("{time}").build(1000))
                                .retrieve()
                                .body(String.class)));
        String result = future2.join();
        long endTime = System.currentTimeMillis();
        Duration duration = Duration.ofSeconds(endTime - startTime);
        return new MessageWithDelay(result, duration.getSeconds());
    }

    /*
    In case of virtual thread below code is a right way to do it.
    Easy to read, debug and test.
     */
    public MessageWithDelay syncBlockingStyle() throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        try (var es = Executors.newVirtualThreadPerTaskExecutor()) {

            Callable<String> firstTask = () -> restClient.get()
                    .uri(b -> b
                            .queryParam("error", false)
                            .queryParam("returnMessage", "hello")
                            .pathSegment("mock").pathSegment("{time}").build(1000))
                    .retrieve()
                    .body(String.class);

            String resultFirst = es.submit(firstTask).get();

            Callable<String> secondTask = () -> restClient.get()
                    .uri(b -> b
                            .queryParam("error", false)
                            .queryParam("returnMessage", resultFirst)
                            .pathSegment("mock").pathSegment("{time}").build(1000))
                    .retrieve()
                    .body(String.class);
            String resultSecond = es.submit(secondTask).get();

            long endTime = System.currentTimeMillis();
            Duration duration = Duration.ofSeconds(endTime - startTime);
            return new MessageWithDelay(resultSecond, duration.getSeconds());
        }
    }
}
