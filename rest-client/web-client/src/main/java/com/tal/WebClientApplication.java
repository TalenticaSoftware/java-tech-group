package com.tal;

import com.tal.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public class WebClientApplication {

    public static void main(String[] args) {
        WebClient client = WebClient.create("http://localhost:8080");
        List<User> users = client.get()
                .uri("/users")
                .header("Authorization", "Bearer Test")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<User>>() {})
                .block();

        System.out.println("Response is : " + users);
    }
}
