package com.tal.service;

import com.tal.entity.User;
import com.tal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // Logic to retrieve user by ID
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for this Id"));
        return user;
    }

    // Logic to update user by ID
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for this Id"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser = userRepository.save(existingUser);
        return existingUser;
    }

    // Logic to create a new user
    public User createUser(User user) {
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    // Logic to delete user by ID
    public void deleteUser(Long id) {
        // Replace this with your implementation
        // Example implementation:
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found for this Id"));
        userRepository.delete(user);
    }

    // Logic to retrieve all users
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
