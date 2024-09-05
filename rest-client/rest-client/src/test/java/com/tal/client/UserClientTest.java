package com.tal.client;

import com.tal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class UserClientTest {

    @Autowired
    private UserClient userClient;

    @Test
    void getUsersTest() {
        ResponseEntity<List<User>> response = userClient.getUsers();

        System.out.println("Response is : " + response);
        response.getBody().stream().forEach(user -> System.out.println(user));
    }


    @Test
    void getUserTest() {
        String idForGet = "1";
        ResponseEntity<User> response = userClient.getUser(idForGet);

        System.out.println("Response is : " + response);
        System.out.println(response.getBody());
    }

    @Test
    void createUserTest() {
        User newUser = User.builder().name("John").email("john.doe@example.com").build();
        ResponseEntity<User> response = userClient.createUser(newUser);

        System.out.println("Response is : " + response);
        System.out.println(response.getBody());
    }

    @Test
    void updateUserTest() {
        String idForUpdate = "4";
        User updateUser = User.builder().name("Sam").email("sam.doe@example.com").build();
        ResponseEntity<User> response = userClient.updateUser(idForUpdate, updateUser);

        System.out.println("Response is : " + response);
        System.out.println(response.getBody());
    }

    @Test
    void deleteUserTest() {
        String idForDelete = "4";
        ResponseEntity deleteResponse = userClient.deleteUser(idForDelete);

        System.out.println("Response is : " + deleteResponse);
    }
}
