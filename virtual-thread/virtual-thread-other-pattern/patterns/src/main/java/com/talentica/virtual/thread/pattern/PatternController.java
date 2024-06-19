package com.talentica.virtual.thread.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class PatternController {

    @Autowired
    PatternService patternService;

    @GetMapping("/fanout/wrong")
    public MessageWithDelay fanoutWrong() {
        return patternService.fanoutWrong();
    }
    @GetMapping("/fanout/right")
    public MessageWithDelay fanoutRight() {
        return patternService.fanoutRight();
    }

    /**
     * Not wrong as such, but won't be a performance improvement in case of virtual threads
     * @return
     */
    @GetMapping("/async/wrong")
    public MessageWithDelay asyncNonBlockingStyle() {
        return patternService.asyncNonBlockingStyle();
    }
    @GetMapping("/async/right")
    public MessageWithDelay syncBlockingStyle() throws ExecutionException, InterruptedException {
        return patternService.syncBlockingStyle();
    }
}
