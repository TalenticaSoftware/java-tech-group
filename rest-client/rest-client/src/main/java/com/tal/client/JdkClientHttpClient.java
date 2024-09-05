package com.tal.client;

import com.tal.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class JdkClientHttpClient {

    private String AUTHORIZATION = "Bearer Test Rest JDK Client";
    private String BASE_URL = "http://localhost:8080/users";
    private RestClient userClient;

    public JdkClientHttpClient() {
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(10000);

        this.userClient = RestClient.builder()
                .defaultHeader("Authorization", AUTHORIZATION)
                .baseUrl(BASE_URL)
                .requestFactory(requestFactory)
                .build();
    }

    public ResponseEntity getUsers() {
        /**
         *  Get Users
         */
        ResponseEntity<List<User>> response = userClient.get()
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<User>>() {
                });

        return response;
    }
}
