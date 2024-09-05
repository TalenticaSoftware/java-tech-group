package com.tal.controller;

import com.tal.client.UserClient;
import com.tal.model.User;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FeignController {

    private final UserClient userClient;

    private String AUTHORIZATION_HEADER = "Bearer test";

    @GetMapping
    public List<User> fetchUsers() {
        return userClient.getUsers(AUTHORIZATION_HEADER);
    }

    @GetMapping("/{id}")
    public User fetchUser(@PathVariable String id) {
        return userClient.getUser(AUTHORIZATION_HEADER, id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return userClient.updateUser(AUTHORIZATION_HEADER, id, user);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userClient.createUser(AUTHORIZATION_HEADER, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userClient.deleteUser(AUTHORIZATION_HEADER, id);
    }

}
