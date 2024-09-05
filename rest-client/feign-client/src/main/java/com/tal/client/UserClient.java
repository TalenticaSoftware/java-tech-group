package com.tal.client;

import com.tal.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "demo-service", url = "http://localhost:8080")
public interface UserClient {

    @GetMapping("/users")
    List<User> getUsers(@RequestHeader("Authorization") String authorization);
    @GetMapping("/users/{id}")
    User getUser(@RequestHeader("Authorization") String authorization, @PathVariable String id);
    @PostMapping("/users")
    User createUser(@RequestHeader("Authorization") String authorization, @RequestBody User user);
    @PutMapping("/users/{id}")
    User updateUser(@RequestHeader("Authorization") String authorization, @PathVariable String id, @RequestBody User user);
    @DeleteMapping("/users/{id}")
    void deleteUser(@RequestHeader("Authorization") String authorization, @RequestBody String id);
}
