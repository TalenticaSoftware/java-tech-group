package com.tal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateApplication {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer Test");
        
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            System.out.println("Response : " + responseBody);
        } else {
            System.out.println("GET request failed. Response Code: " + response.getStatusCodeValue());
        }
    }
}