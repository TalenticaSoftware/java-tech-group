package com.tal.client;

import com.tal.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class RestTemplateClient {

    private String AUTHORIZATION = "Bearer Test Rest Template Client";
    private String BASE_URL = "http://localhost:8080/users";
    private RestClient userClient;

    public RestTemplateClient() {
        RestTemplate restTemplate = new RestTemplate();
        // mode code
        //

        this.userClient = RestClient.builder(restTemplate).build();
    }

    public ResponseEntity<List<User>> getUsers() {
        ResponseEntity<List<User>> response = userClient.get()
                .uri(BASE_URL)
                .header("Authorization", AUTHORIZATION)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<User>>() {});

        return response;
    }

}
