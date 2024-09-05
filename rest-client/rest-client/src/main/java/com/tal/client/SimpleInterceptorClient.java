package com.tal.client;

import com.tal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class SimpleInterceptorClient {

    private final RestClient restClient;

    public SimpleInterceptorClient(ClientHttpRequestInterceptor interceptor) {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:8080/users")
                .requestInterceptor(interceptor)
                .build();
    }

    public List<User> findAllUsers() {
        return restClient.get()
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<User>>() {})
                .getBody();
    }
}
