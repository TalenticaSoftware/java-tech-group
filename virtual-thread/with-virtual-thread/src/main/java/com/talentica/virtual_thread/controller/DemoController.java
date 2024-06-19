package com.talentica.virtual_thread.controller;

import com.talentica.virtual_thread.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;
    @RequestMapping("/greeting")
    public String execute() {
        demoService.execute();
        return "Hello, World!";
    }
}
