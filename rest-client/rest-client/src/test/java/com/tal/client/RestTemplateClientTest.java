package com.tal.client;

import com.tal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class RestTemplateClientTest {

    @Autowired
    private RestTemplateClient restTemplateClient;

    @Test
    void getUsersTest() {

        ResponseEntity<List<User>> response = restTemplateClient.getUsers();

        System.out.println("Response is : " + response);
        response.getBody().stream().forEach(user -> System.out.println(user));
    }
}
