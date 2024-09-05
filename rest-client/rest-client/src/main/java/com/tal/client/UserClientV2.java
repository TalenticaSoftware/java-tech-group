package com.tal.client;

import com.tal.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserClientV2 {
    private String AUTHORIZATION = "Bearer Test Rest Client V2";
    private String BASE_URL = "http://localhost:8080/users";
    private RestClient userClient;

    public UserClientV2() {
        this.userClient = RestClient.builder()
                .defaultHeader("Authorization", AUTHORIZATION)
                .baseUrl(BASE_URL)
                .build();
    }

    public List<User> getUsers() {
        List<User> users = userClient.get()
                .exchange((request, response) -> {
                    if (response.getStatusCode().is4xxClientError()) {
                        throw new RuntimeException("HTTP Error :" + response.getStatusCode().toString());
                    }
                    else {
                        return response.bodyTo(new ParameterizedTypeReference<List<User>>() {});
                    }
                });

        return users;
    }

    public ResponseEntity deleteUserHandleStatus(String id) {
        ResponseEntity deleteResponse = userClient.delete()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> { throw new RuntimeException("HTTP Error :" + response.getStatusCode().toString());}))
                .toBodilessEntity();

        return deleteResponse;
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
}
