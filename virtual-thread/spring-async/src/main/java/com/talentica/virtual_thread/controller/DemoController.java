package com.talentica.virtual_thread.controller;

import com.talentica.virtual_thread.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;
    @RequestMapping("/greeting")
    @Async
    public CompletableFuture execute() {
        demoService.execute();
        return CompletableFuture.completedFuture("Hello, World!");
    }
}
