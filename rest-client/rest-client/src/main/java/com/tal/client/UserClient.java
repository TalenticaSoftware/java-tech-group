package com.tal.client;

import com.tal.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserClient {
    private String AUTHORIZATION = "Bearer Test Rest Client";
    private String BASE_URL = "http://localhost:8080/users";
    private RestClient userClient;

    public UserClient() {
        this.userClient = RestClient.builder()
                .defaultHeader("Authorization", AUTHORIZATION)
                .baseUrl(BASE_URL)
                .build();
    }

    public ResponseEntity getUsers() {
        ResponseEntity<List<User>> response = userClient.get()
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<User>>() {
                });

        return response;
    }

    public ResponseEntity getUser(String id) {
        ResponseEntity<User> response = userClient.get()
                .uri("/{id}", id)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<User>() {
                });

        return response;
    }

    public ResponseEntity createUser(User user) {
        ResponseEntity createResponse = userClient.post()
                .body(user)
                .retrieve()
                .toEntity(User.class);

        return createResponse;
    }

    public ResponseEntity updateUser(String id, User user) {
        ResponseEntity updateResponse = userClient.put()
                .uri("/{id}", id)
                .body(user)
                .retrieve()
                .toEntity(User.class);

        return updateResponse;
    }

    public ResponseEntity deleteUser(String id) {
        ResponseEntity deleteResponse = userClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .toBodilessEntity();

        return deleteResponse;
    }

}
