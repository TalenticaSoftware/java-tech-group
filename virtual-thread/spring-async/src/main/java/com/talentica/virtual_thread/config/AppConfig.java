package com.talentica.virtual_thread.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/*
By default, Spring will be searching for an associated thread pool definition: either unique org.springframework.core.task.TaskExecutor bean in the context, or an java.util.concurrent.Executor bean named "taskExecutor" otherwise.

If neither of the two is resolvable, a org.springframework.core.task.SimpleAsyncTaskExecutor will be used to process async method invocations.

Now if you want to provide your own customization, you can define (implement) an AsyncConfigurer that basically allows to define an executor and exception handler (out of scope for this question).

Reference: https://stackoverflow.com/questions/57988341/what-are-the-defaults-in-spring-async
 */
@Configuration
public class AppConfig {

    @Bean
    Executor taskExecutor() {
        return Executors.newFixedThreadPool(5);
    }
}
