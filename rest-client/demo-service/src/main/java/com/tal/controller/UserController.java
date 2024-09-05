package com.tal.controller;

import com.tal.entity.User;
import com.tal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization) {
        log.info("Getting user with ID: {}", id);
        log.info("Authorization header: {}", authorization);
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user, @RequestHeader("Authorization") String authorization) {
        log.info("Updating user with ID: {}", id);
        log.info("Authorization header: {}", authorization);
        return userService.updateUser(id, user);
    }

    @PostMapping
    public User createUser(@RequestBody User user, @RequestHeader("Authorization") String authorization) {
        log.info("Creating a new user : {}", user);
        log.info("Authorization header: {}", authorization);
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id, @RequestHeader("Authorization") String authorization) {
        log.info("Deleting user with ID: {}", id);
        log.info("Authorization header: {}", authorization);
        userService.deleteUser(id);
    }

    @GetMapping
    public List<User> getUsers(@RequestHeader("Authorization") String authorization) {
        log.info("Getting users");
        log.info("Authorization header: {}", authorization);
        return userService.getAllUsers();
    }
}
