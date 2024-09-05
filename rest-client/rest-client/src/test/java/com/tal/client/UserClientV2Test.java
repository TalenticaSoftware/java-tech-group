package com.tal.client;

import com.tal.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest
public class UserClientV2Test {

    @Autowired
    private UserClientV2 userClientV2;

    @Test
    void getUsersUsingExchangeTest() {
       List<User> users = userClientV2.getUsers();

        System.out.println("Response is : ");
        users.stream().forEach(user -> System.out.println(user));
    }


    @Test
    void getUserTest() {
        String idForGet = "1";
        ResponseEntity<User> response = userClientV2.getUser(idForGet);

        System.out.println("Response is : " + response);
        System.out.println(response.getBody());
    }

    @Test
    void deleteUserHandleStatusTest() {
        String idForDelete = "12";
        try {
            ResponseEntity deleteResponse = userClientV2.deleteUserHandleStatus(idForDelete);

            System.out.println("Response is : " + deleteResponse);
        } catch (Exception exception) {
            System.out.println("Exception :" + exception.getLocalizedMessage());
        }
    }
}
