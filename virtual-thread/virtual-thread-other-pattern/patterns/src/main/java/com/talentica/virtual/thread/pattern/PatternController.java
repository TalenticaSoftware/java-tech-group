package com.talentica.virtual.thread.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class PatternController {

    @Autowired
    DependentCallsService dependentCallsService;

    @Autowired
    IndependentCallsService independentCallsService;

    @GetMapping("/fanout/wrong")
    public MessageWithDelay fanoutWrong() {
        return independentCallsService.fanoutWrong();
    }
    @GetMapping("/fanout/right")
    public MessageWithDelay fanoutRight() {
        return independentCallsService.fanoutRight();
    }


    @GetMapping("/dependent/wrong")
    public MessageWithDelay asyncNonBlockingStyle() {
        return dependentCallsService.asyncNonBlockingStyle();
    }
    @GetMapping("/dependent/right")
    public MessageWithDelay syncBlockingStyle() throws ExecutionException, InterruptedException {
        return dependentCallsService.syncBlockingStyle();
    }
}
