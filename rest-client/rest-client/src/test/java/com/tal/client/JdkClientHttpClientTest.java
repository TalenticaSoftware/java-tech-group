package com.tal.client;

import com.tal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class JdkClientHttpClientTest {

    @Autowired
    private JdkClientHttpClient jdkClientHttpClient;

    @Test
    void getUsersTest() {
        ResponseEntity<List<User>> response = jdkClientHttpClient.getUsers();

        System.out.println("Response is : " + response);
        response.getBody().stream().forEach(user -> System.out.println(user));
    }
}
