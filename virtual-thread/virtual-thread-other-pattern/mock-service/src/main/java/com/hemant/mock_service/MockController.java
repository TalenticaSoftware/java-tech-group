package com.hemant.mock_service;

import com.hemant.mock_service.exception.AppErrorCodes;
import com.hemant.mock_service.exception.AppException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MockController {
    @GetMapping("/mock/{delay}")
    public String mockService(@PathVariable Integer delay,
                              @RequestParam(required = false, defaultValue = "okay") String returnMessage,
                              @RequestParam(required = false, defaultValue = "false") boolean raiseError) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (raiseError) {
            throw new AppException(AppErrorCodes.NOT_VALID_REQUEST);
        }
        return returnMessage;
    }
}
