package com.tal.interceptor;

import com.tal.client.SimpleInterceptorClient;
import com.tal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CustomInterceptorTest {

    @Autowired
    SimpleInterceptorClient simpleInterceptorClient;

    @Test
    void findAllTest() {
        List<User> users= simpleInterceptorClient.findAllUsers();
        System.out.println("Response is : ");
        users.stream().forEach(user -> System.out.println(user));
    }
}
