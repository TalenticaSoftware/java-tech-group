package com.tal.client;

import com.tal.model.User;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserClientTest {

        @Autowired
        private UserClient userClient;

        private String AUTHORIZATION_STRING = "Test Feign-Client";

        @Test
        public void testGetUsers() {
            List<User> users = userClient.getUsers(AUTHORIZATION_STRING);

            System.out.println("Response : " + users);
        }

        @Test
        public void testGetUser() {
            String idForGet = "3";
            User user = userClient.getUser(AUTHORIZATION_STRING, idForGet);

            System.out.println("Response : " + user);
        }

        @Test
        void createUserTest() {
                User newUser = User.builder().name("John").email("john.doe@example.com").build();
                User user = userClient.createUser(AUTHORIZATION_STRING, newUser);

                System.out.println("Response is : " + user);
        }

        @Test
        void updateUserTest() {
                String idForUpdate = "1";
                User updateUser = User.builder().name("Max").email("max.doe@example.com").build();
                User user = userClient.updateUser(AUTHORIZATION_STRING, idForUpdate, updateUser);

                System.out.println("Response is : " + user);
        }

        @Test
        void deleteUserTest() {
                String idForDelete = "4";
                userClient.deleteUser(AUTHORIZATION_STRING, idForDelete);

        }
}
